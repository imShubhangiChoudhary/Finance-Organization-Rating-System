pipeline{
  agent any
  stages{
    stage("one"){
      steps{
        echo("THis is first stage simple echo")
      }      
    }
    stage("test user input"){
      steps{
        input("second step wait for user input. Do u wants to continue?")
      }      
    }
    stage("three"){
      steps{
        echo("THis is 3rd stage simple echo")
      }      
    }
  }
}
    
