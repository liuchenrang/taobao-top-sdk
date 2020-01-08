build:
	mvn deploy -DaltDeploymentRepository=liuchenrang-mvn-repo::default::file:/Users/chen/.chen-maven-repo/repository/
	cd /Users/chen/.chen-maven-repo/repository/ && git add * && git commit -am 'up' && git push origin master
