pipeline {
  agent {
    docker { image 'node:16-alpine'
           label 'docker'
           }
  }
  stages {
    stage('Test') {
      environment {
         HOME="."
         }
      steps {
        sh 'node --version'
      }
    }
  }
}
