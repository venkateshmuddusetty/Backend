properties([pipelineTriggers([githubPush()])])
pipeline {
    agent any
    environment {
        registryUrl = "hidptsteastus2acr.azurecr.io"
        
        }
    
        stages {
          stage( 'Gitcheckout') {
                steps {
                 //   checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'test-tken-v', url: '']]])
                    echo "test"
                }
            }  
            stage( 'Build') {
                steps {
                    script {
                        datas = readYaml (file : "$WORKSPACE/config.yml")
                        echo "build type is: ${datas.Build_type}"
                        
                        
                        if( "${datas.Build_type}" == "maven" )
                        {
                        sh 'mvn clean install -DskipTests=True'
                        }
                        else( "${datas.Build_type}" == "gradle" ) 
                        {    
                        sh 'gradle build'
                        }
                        
                    }
                }
            }
             stage( 'Build docker image') {
                steps {
                    sh "docker build -t $registryUrl/hello:${BUILD_NUMBER} ."
                    
                }
                
            }
			stage('Upload Image to ACR') {
                steps{
                    withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'docker_pass', usernameVariable: 'docker_user')]) {
                        sh "docker login $registryUrl -u ${docker_user} -p ${docker_pass}"
}
                    
                  //  sh 'docker tag  hello:latest $registryUrl/hello:${BUILD_NUMBER}'
                    sh 'docker push $registryUrl/hello:${BUILD_NUMBER}'
                    
                }
            }
			}
		}
		
	
