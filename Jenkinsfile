pipeline {
  agent {
    docker { image 'node:16-alpine' }
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
