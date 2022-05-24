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
                        {
                        sh 'mvn clean install -DskipTests=True'
                        }
                        else( "${datas.Build_tool}" == "gradle" ) 
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
            stage( 'Login to AKS repo') {
                steps {
                        sh 'rm -rf *'
                     withCredentials([usernamePassword(credentialsId: 'test-tken-v', passwordVariable: 'password', usernameVariable: 'username')]) {
                      //git remote set-url origin https://venkateshmuddusetty:${password}@github.com/venkateshmuddusetty/test.git
                         sh '''  
                         git config --global user.name "${username}"
                         git config --global user.email "venkat149dev@gmail.com"
                         git clone https://${password}@github.com/venkateshmuddusetty/test.git
                         cd test/
                         git branch
                         rm -rf deployment.yml
                         git status
                         cp -r /opt/k8s_deploy/deployment.yml ${WORKSPACE}/test/
                         git status
                         sed -i "s|LATESTVERSION|$registryUrl/hello:${BUILD_NUMBER}|g" ${WORKSPACE}/test/deployment.yml
                         cat deployment.yml
                         git add deployment.yml
                         git commit -m "Build_number"
                         git push -u origin '''
                     } 
                }
            }
          /*  stage( 'Update to AKS repo') {
                steps {
                    sh '''
                    set -e
                    cp -r /opt/k8s_deploy/deployment.yml ${WORKSPACE}/test/deployment.yml
                    cat test/deployment.yml
                    sed -e "s|LATESTVERSION|$registryUrl/hello:${BUILD_NUMBER}|g" test/deployment.yml
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
            } */
        }
}
