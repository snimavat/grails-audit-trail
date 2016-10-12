
println System.getenv('TRAVIS')
println System.getenv('GRAILS_CENTRAL_USERNAME')
println System.getenv('GRAILS_CENTRAL_PASSWORD')


if (System.getenv('TRAVIS')) {
	println "Inside"
	grails.project.repos.grailsCentral.username = System.getenv('GRAILS_CENTRAL_USERNAME')
	grails.project.repos.grailsCentral.password = System.getenv('GRAILS_CENTRAL_PASSWORD')
}


grails.project.work.dir = '.grails'
grails.project.dependency.resolver = "maven"
grails.project.dependency.resolution = {

	inherits 'global'
	log 'warn'

	repositories {
        grailsPlugins()
        grailsCentral()

        mavenLocal()
        mavenCentral()
	}

	plugins {
        build(":release:3.1.2", ":rest-client-builder:2.1.1") { export = false }
		compile (':spring-security-core:2.0-RC4'){
			export = false
		}
        runtime(":hibernate4:4.3.10") { export = false }
	}
}
