VAGRANT_COMMAND = ARGV[0]

Vagrant.configure("2") do |config|
  # Popular base box, should work!
  config.vm.box = "ubuntu/trusty64"

  config.vm.provider "virtualbox" do |v|
    v.memory = 2048
    v.cpus = 2
  end

  # Forward expected guest Jenkins to host 8080
  config.vm.network "forwarded_port", guest: 8080, host: 8080

  # Forward Gitea
  config.vm.network "forwarded_port", guest: 3000, host: 3000
  config.vm.network "forwarded_port", guest: 2222, host: 2222

  # Use root on subsequent vagrant ssh calls because easy and secure, right? Throwaway dev box, not for production! :-)
  if VAGRANT_COMMAND == "ssh"
    config.ssh.username = 'root'
    config.ssh.insert_key = 'false'
  end

  # Install Docker and Docker Compose, validate, and support login directly as root using the Vagrant insecure key
  config.vm.provision "shell", inline: <<-SHELL
    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add -
    add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
    apt-get update

    # Temporarily tied to a specific version of Docker due to https://github.com/docker/for-linux/issues/591
    apt-get install -y docker-ce=18.06.1~ce~3-0~ubuntu dos2unix

    curl -L https://github.com/docker/compose/releases/download/1.22.0/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose
    chmod +x /usr/local/bin/docker-compose

    docker --version
    docker-compose --version

    cp /home/vagrant/.ssh/authorized_keys /root/.ssh
  SHELL

  # Place a little script that'll run on boot (vagrant up / reload) and sync /vagrant -> /var/vagrant (for inner Docker sync)
  config.vm.provision "file", source: "sync.sh", destination: "/tmp/sync.sh"
  config.vm.provision "shell", inline: "mv /tmp/sync.sh /opt/sync.sh"
  config.vm.provision "shell", inline: "chmod a+x /opt/sync.sh"
  config.vm.provision "shell", inline: "/opt/sync.sh > /dev/null 2>&1 &", run: "always"

end
