def VERSION
pipeline {
    agent any
    tools {
        gradle "gradle"
    }
    stages {
        stage('Git Checkout') {
            steps {
                git(
                    url: "https://github.com/WagnerWD40/gerenciador-acessos",
                    branch: "master"
                )
            }
        }
        stage('Get Version') {
            steps {
                script {
                    VERSION = sh(
                        returnStdout: true,
                        script: "cat build.gradle | grep -o 'version = [^,]*'"
                    ).replace("version = ", "").replace("'", "").trim()
                    echo "Version: ${VERSION}"
                }
            }
        }
        stage('Build') {
            steps {
                sh "gradle clean bootJar"
            }

        }
        stage('Create Image') {
            steps {
                script {
                    def version_arg="VERSION=${VERSION}"
                    sh "docker build -t wagnerwd40/gerenciador-acessos:${VERSION} --build-arg=${version_arg} ."
                }
            }
        }
        stage('Publish Image') {
            steps {
                sh "docker push wagnerwd40/gerenciador-acessos:${VERSION}"
            }
        }
    }
}