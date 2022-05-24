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
                        echo "build type is: ${datas.Build_tool}"
                        
                        
                        if( "${datas.Build_tool}" == "maven" ) 
                        sh 'mvn clean install -DskipTests=True'
                        
                        else( "${datas.Build_tool}" == "gradle" ) 
                            sh 'gradle build'
                        
                    }
                }
            }
            stage( 'Build docker image') {
                steps {
                    sh 'docker build -t hello:latest .'
                    
                }
                
            }
            stage('Upload Image to ACR') {
                steps{
                    sh '''
                        docker login $registryUrl -u hidpdeveastusbotacr -p +EaOLpFAd9ks5vrkfWBilFcJPoBQnKgT
                        '''
                    sh 'docker tag  hello:latest $registryUrl/hello:${BUILD_NUMBER}'
                    sh 'docker push $registryUrl/hello:${BUILD_NUMBER}'
                    
                }
            }
            stage( 'Login to AKS repo') {
                steps {
                        sh 'rm -rf *'
                    
                        //sh "mkdir -p $WORKSPACE/test"
                        //sh "cd $WORKSPACE/test"
                        checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], gitTool: 'Default', userRemoteConfigs: [[credentialsId: 'test-tken-v', url: 'https://github.com/venkateshmuddusetty/test.git']]])
                      withCredentials([usernamePassword(credentialsId: 'test-tken-v', passwordVariable: 'password', usernameVariable: 'username')]) {
                      sh "  git clone https://${password}@github.com/venkateshmuddusetty/test.git"
                     }
                    }
               }
            stage( 'Update to AKS repo') {
                steps {
                        sh '''
                            set -e
                         
                            cat deployment.yml
                            sed -e "s|hidpdeveastusbotacr.azurecr.io/hello:latest|$registryUrl/hello:${BUILD_NUMBER}|g" deployment.yml
                            '''
                            
                            withCredentials([usernamePassword(credentialsId: 'test-tken-v', passwordVariable: 'password', usernameVariable: 'username')]) {
                                sh 'git config --global user.name "venkateshmuddusetty"'
                                  sh 'git config --global user.email "venkat149dev@gmail.com"'
                               sh 'git remote set-url origin https://venkateshmuddusetty:${password}@github.com/venkateshmuddusetty/test.git'
                                sh "git add ."
                            sh "git status"
                            sh 'git commit -m  "adding the image"'
                            sh 'git branch'
    
                            sh "  git push origin HEAD:main"
                             }
                            
                      
                      
                    }
                }
            }
        }
