Devops framework example
========================

This repository holds an example supporting devops-framework for use with Jenkins shared libraries.

The framework provides a set of callouts which can be used to control your devops process depending
on the callouts that you use.

There are 5 main interface classes provided. These are...

	* Build - Which is used to control your build process
	* Deploy - Which is used to control your deployment process
	* Test - Which is used to control your test process
	* Integration - Which is used to control your integration process
	* ReleaseCandidate - Which is used to control your Release candidate process

Why have a framework?
=====================

The framework is provided as a why of allowing you to control how products are on-boarded into CI/CD.

You may have many products that need to be converted over to using CI/CD and without a framework in place
those products may end up using lots of different standards or pipelines for implementing their CI/CD process.
This framework allows you to have one standard for implementation, whilst at the same time allowing complete
flexiblity about the processes that are run.

Also, by extending the base framework you can implement specific customisations that you might want to apply
to every product - like security scans - that cannot be overriden by manipulating the build pipeline.

You can also add services and library routines that will then be available to everyone - building on the functionality
already available.

Build Class
===========

The Build class is provided to control your build process and has the following methods...

	- prepareWorkArea() - A callout provided to help prepare your workarea for a build
	- getCode() - A callout provided to help you pull your code
	- preBuild() - A callout provided to help prepare for a build
	- runBuild() - A callout provided to help run a build process
	- postBuild() - A callout provided to help run a post build process
	- runUnitTests() - A callout provided to help run any unit tests
	- evaluateUnitTests() - A callout provided to help evaluate any unit tests results
	- runStaticCodeTests() - A callout provided to help run any static code analysis process
	- evaluateStaticCodeTests() - A callout provided to help evaluate any analysis results
	- bakeImage() - A callout provided to help bake an image
	- uploadAssets() - A callout provided to help upload any assets created during the build
	- logResults() - A callout provided to help log any results 
	- promote() - A callout provided to help promote a build to the next phase 
	- runPipeline() - Run the pipeline

All callbacks are run in the above order, no matter how your register them.

Deploy Class
============

The Deploy class is provided to control your deploy process and has the following methods...

	- prepareForDeploy() - A callout provided to help prepare your environment for a deploy
	- getAssets() - A callout provided to help you pull your deploy assets
	- preDeploy() - A callout provided to help prepare for a deploy
	- runDeploy() - A callout provided to help run a deploy process
	- postDeploy() - A callout provided to help run a post deploy process
	- runSmokeTests() - A callout provided to help run any smoke tests
	- evaluateSmokeTests() - A callout provided to help evaluate any smoke tests results
	- logResults() - A callout provided to help log any results 
	- promote() - A callout provided to help promote a deploy to the next phase 
	- runPipeline() - Run the pipeline

All callbacks are run in the above order, no matter how your register them.

Test Class
==========

The Test class is provided to control your test process and has the following methods...

	- prepareForTest() - A callout provided to help prepare your environment for testing
	- getAssets() - A callout provided to help you pull your test assets
	- preTest() - A callout provided to help prepare for a test
	- runTest() - A callout provided to help run a test process
	- postTest() - A callout provided to help run a post test process
	- evaluateTests() - A callout provided to help evaluate any tests results
	- logResults() - A callout provided to help log any results 
	- promote() - A callout provided to help promote a test to the next phase 
	- runPipeline() - Run the pipeline

All callbacks are run in the above order, no matter how your register them.

Integration Class
=================

The Integration class is provided to control your integration process and has the following methods...

	- getComponentList() - A callout provided to help get the component list for processing
	- prepareForDeploy() - A callout provided to help prepare an environment for use
	- getDeployAssets() - A callout provided to help pull your deployment assets
	- preDeploy() - A callout provided to help prepare for the deploy
	- runDeploy() - A callout provided to help run the deploy
	- postDeploy() - A callout provided to help perform any post deployment actions
	- runSmokeTests() - A callout provided to help run smoke tests
	- evaluateSmokeTests() - A callout provided to help evaluate the smoke test results
	- logDeployResults() - A callout provided to help log the deployment results
	- prepareForTest() - A callout provided to help prepare for testing
	- getTestAssets() - A callout provided to help pull test assets
	- preTest() - A callout provided to help prepare for the testing
	- runTests() - A callout provided to help run the testing
	- postTest() - A callout provided to help perform any post testing activities
	- evaluateTestResults() - A callout provided to help evaluate test results
	- logTestResults() - A callout provided to help log test results
	- promote() - A callout provided to help promote a test to the next phase 
	- runPipeline() - Run the pipeline

All callbacks are run in the above order, no matter how your register them.

ReleaseCandidate Class
======================

The ReleaseCandidate class is provided to control your release process and has the following methods...

	- getComponentList() - A callout provided to help get the component list for processing
	- prepareForDeploy() - A callout provided to help prepare an environment for use
	- getDeployAssets() - A callout provided to help pull your deployment assets
	- preDeploy() - A callout provided to help prepare for the deploy
	- runDeploy() - A callout provided to help run the deploy
	- postDeploy() - A callout provided to help perform any post deployment actions
	- runSmokeTests() - A callout provided to help run smoke tests
	- evaluateSmokeTests() - A callout provided to help evaluate the smoke test results
	- logDeployResults() - A callout provided to help log the deployment results
	- prepareForTest() - A callout provided to help prepare for testing
	- getTestAssets() - A callout provided to help pull test assets
	- preTest() - A callout provided to help prepare for the testing
	- runTests() - A callout provided to help run the testing
	- postTest() - A callout provided to help perform any post testing activities
	- evaluateTestResults() - A callout provided to help evaluate test results
	- logTestResults() - A callout provided to help log test results
	- rollback() - A callout provided to help perform a rollback if required
	- finish() - A callout provided to help perform any final actions if needed
	- runPipeline() - Run the pipeline

All callbacks are run in the above order, no matter how your register them.

How to Install
==============

To install this Jenkins share library, do the following...

	1) git clone https://github.com/tpayne/devops-framework.git
	2) cd devops-framework
	3) mvn package
	4) cd target/
	5) Unzip devops-framework-0.0.1-SNAPSHOT-artifact.zip into a working directory
	6) Use the instructions https://jenkins.io/doc/book/pipeline/shared-libraries/#global-shared-libraries to install the shared library into your Jenkins system

How to Use
==========

The shared library works can providing controller classes that you can use
in a pipeline job. As such, you need to create a pipeline job and register the
callbacks you want to use.

For example, a sample pipeline might look like

	// Sample pipeline...
	@Library('devops-framework')

	def config = [
	    property1: 'value1',
	    property2: 'value2',
	    property3: 'value3'
	]

	def bld = new org.devops.Build(this, config)

	node {    
		// Register my build process...    
		bld.runBuild(body:{ 
			println ">Start build...<"
			sh label: '', script: '''cd /tmp
			echo hello2
			pwd
			sh /Users/alexgray/build.sh
			'''        
		}, finalHandler:{ println ">Build Job Done<" })

		// Register my get code callback...
		bld.getCode(body:{ println ">Get my code<" })

		// Register my bake callback...
		bld.bakeImage(body:{ println ">Run my bake<" })

		// Register a pre-build callback...
		bld.preBuild(body:{
			println ">Pre-build - clean my files up...<"
			sh label: 'Pre-build sh:', script: '''cd /tmp
			if [ -f main ]; then
				ls main
				rm -f main
			fi
			'''        
		})
		// Run my pipeline...
		bld.runPipeline()
	}

This will run the getCode(), preBuild(), runBuild() and bakeImage() callbacks in this
order.

Each callback takes the following...

	- body:{} - used to specify the Groovy code to run the process
	- finalHandler:{} - used to specify any Groovy code which will be invoked after the process has run
	- exceptionHandler:{} - used to specify any Groovy code which will be invoked if any exception occurs

Liability Warning
=================
The contents of this repository (documents and examples) are provided “as-is” with no warrantee implied 
or otherwise about the accuracy or functionality of the examples.

You use them at your own risk. If anything results to your machine or environment or anything else as a 
result of ignoring this warning, then the fault is yours only and has nothing to do with me.
