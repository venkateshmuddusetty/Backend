properties([pipelineTriggers([githubPush()])])
pipeline {
    agent any
    environment {
        registryUrl = "hidpdeveastusbotacr.azurecr.io"
        
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
              stage('SonarQube analysis') {
                steps {
                    withSonarQubeEnv('sonarqube-9.0.1') {
                    sh "mvn sonar:sonar -Dsonar.projectKey=maven-demo -Dsonar.host.url=http://20.55.51.162:9000 -Dsonar.login=75b555ac651f5a3435d141fe1387d93274af9905"
    }
					}
				}
			}
		}
		
