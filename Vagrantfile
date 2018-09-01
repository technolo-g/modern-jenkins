VAGRANT_COMMAND = ARGV[0]

Vagrant.configure("2") do |config|
  # Popular base box, should work!
  config.vm.box = "ubuntu/trusty64"

  # Forward expected guest Jenkins to host 8080
  config.vm.network "forwarded_port", guest: 8080, host: 8080

  # Use root on subsequent vagrant ssh calls because easy and secure, right? Throwaway dev box :-)
  if VAGRANT_COMMAND == "ssh"
    config.ssh.username = 'root'
    config.ssh.insert_key = 'false'
  end

  # Install Docker and Docker Compose, validate, and support login directly as root using the Vagrant insecure key
  config.vm.provision "shell", inline: <<-SHELL
    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add -
    add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
    apt-get update
    apt-get install -y docker-ce dos2unix

    curl -L https://github.com/docker/compose/releases/download/1.22.0/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose
    chmod +x /usr/local/bin/docker-compose

    docker --version
    docker-compose --version

    cp /home/vagrant/.ssh/authorized_keys /root/.ssh
  SHELL
end
