package org.devops.framework.core;

import groovy.util.GroovyTestCase;
import junit.framework.*;
import junit.textui.TestRunner;

/**
 * Unit SCMTests tests for SCM class.
 */
public class SCMGITCloneTests extends GroovyTestCase {

   File propFile = new File("."+"/src/test/resources/unitTest.properties")
   def map = Utilities.mapProperties(propFile)

   /**
    * Utility function for getting tmpDir
    */
   File getTmpDir() {
      if (runUnitTestsOnly()) {
        return new File(System.getProperty("java.io.tmpdir"))
      } else {
        return new File((map.get("tmpDir") != null) ? map.get("tmpDir") : System.getProperty("java.io.tmpdir"))
      }
   }

   /**
    * Utility function for seeing if need to just run unit-tests
    */
   boolean runUnitTestsOnly() {
      if (System.getenv("DEVOPS_FRAMEWORK_UNITTESTS")!=null) {
        return true;
      }
      String unitTests = map.get("unit_tests_only")
      if (unitTests != null && !unitTests.isEmpty()) {
        return(unitTests.contains("true"))
      }
      return false
   }

   /**
    * Unit test for git cloning
    */
   void testScmGitCloneBasic() {
      if (runUnitTestsOnly()) {
        return;
      }
      String scmURI = map.get("git_repoURI")
      File tmpDir = new File(map.get("git_repoDir"))
      
      if (tmpDir.exists()) {
         tmpDir.setWritable(true)
         Utilities.deleteDirs(tmpDir)
         tmpDir.delete()
         assertFalse(tmpDir.exists())
      }

      boolean retStat = SCM.scmClone(ConfigPropertiesConstants.SCMGIT,scmURI)
      assertTrue(retStat)
      retStat = false
      if (tmpDir.exists()) {
         retStat = true
         tmpDir.setWritable(true)
         Utilities.deleteDirs(tmpDir)
         tmpDir.delete()
         assertFalse(tmpDir.exists())
      }
      assertTrue(retStat)
   }

   /**
    * Unit test for git cloning
    */
   void testScmGitCloneBasicTrue() {
      if (runUnitTestsOnly()) {
        return;
      }
      String scmURI = map.get("git_repoURI")
      File tmpDir = new File(map.get("git_repoDir"))
      
      if (tmpDir.exists()) {
         tmpDir.setWritable(true)
         Utilities.deleteDirs(tmpDir)
         tmpDir.delete()
         assertFalse(tmpDir.exists())
      }

      boolean retStat = SCM.scmClone(ConfigPropertiesConstants.SCMGIT,scmURI,null,true)
      assertTrue(retStat)
      retStat = false
      if (tmpDir.exists()) {
         retStat = true
         tmpDir.setWritable(true)
         Utilities.deleteDirs(tmpDir)
         tmpDir.delete()
         assertFalse(tmpDir.exists())
      }
      assertTrue(retStat)
   }

   /**
    * Unit test for git cloning with user/password
    */
   void testScmGitCloneBasicWithUser() {
      if (runUnitTestsOnly()) {
        return;
      }
      String scmURI = map.get("git_repoURI")
      File tmpDir = new File(map.get("git_repoDir"))
      
      if (tmpDir.exists()) {
         tmpDir.setWritable(true)
         Utilities.deleteDirs(tmpDir)
         tmpDir.delete()
         assertFalse(tmpDir.exists())
      }

      // As this is a public URI, it will not matter what users you throw at it...
      String user = map.get("git_repoUser")
      String pwd  = map.get("git_repoUserPwd")

      boolean retStat = SCM.scmClone(ConfigPropertiesConstants.SCMGIT,scmURI,user,pwd)
      assertTrue(retStat)
      retStat = false
      if (tmpDir.exists()) {
         retStat = true
         tmpDir.setWritable(true)
         Utilities.deleteDirs(tmpDir)
         tmpDir.delete()
         assertFalse(tmpDir.exists())
      }
      assertTrue(retStat)
   }

   /**
    * Unit test for git cloning with user/password
    */
   void testScmGitCloneBasicWithUserTrue() {
      if (runUnitTestsOnly()) {
        return;
      }
      String scmURI = map.get("git_repoURI")
      File tmpDir = new File(map.get("git_repoDir"))
      
      if (tmpDir.exists()) {
         tmpDir.setWritable(true)
         Utilities.deleteDirs(tmpDir)
         tmpDir.delete()
         assertFalse(tmpDir.exists())
      }

      // As this is a public URI, it will not matter what users you throw at it...
      String user = map.get("git_repoUser")
      String pwd  = map.get("git_repoUserPwd")

      boolean retStat = SCM.scmClone(ConfigPropertiesConstants.SCMGIT,scmURI,user,pwd,null,true)
      assertTrue(retStat)
      retStat = false
      if (tmpDir.exists()) {
         retStat = true
         tmpDir.setWritable(true)
         Utilities.deleteDirs(tmpDir)
         tmpDir.delete()
         assertFalse(tmpDir.exists())
      }
      assertTrue(retStat)
   }

   /**
    * Unit test for git cloning with target
    */
   void testScmGitCloneWithTarget() {
      if (runUnitTestsOnly()) {
        return;
      }
      String scmURI = map.get("git_repoURI")
      File   tempDir = this.getTmpDir()
      File tmpDir = new File(tempDir.getCanonicalPath()+File.separator+map.get("git_repoDir")+"/")

      if (tmpDir.exists()) {
         tmpDir.setWritable(true)
         Utilities.deleteDirs(tmpDir)         
         tmpDir.delete()
         assertFalse(tmpDir.exists())
      }

      // Test for file not found...
      try {
         boolean retStat = SCM.scmClone(ConfigPropertiesConstants.SCMGIT,scmURI,tmpDir)
         assertTrue(false)
      } catch(FileNotFoundException ex) {
      } catch(Exception) {
         throw ex
      }

      assertTrue(tmpDir.mkdirs() && tmpDir.setWritable(true))
      boolean retStat = SCM.scmClone(ConfigPropertiesConstants.SCMGIT,scmURI,tmpDir)
      assertTrue(retStat)

      if (tmpDir.exists()) {
         retStat = true
         tmpDir.setWritable(true)
         Utilities.deleteDirs(tmpDir)         
         tmpDir.delete()
         assertFalse(tmpDir.exists())
      }
      assertTrue(retStat)
   }

   /**
    * Unit test for git cloning with target
    */
   void testScmGitCloneWithTargetTrue() {
      if (runUnitTestsOnly()) {
        return;
      }
      String scmURI = map.get("git_repoURI")
      File   tempDir = this.getTmpDir()
      File tmpDir = new File(tempDir.getCanonicalPath()+File.separator+map.get("git_repoDir")+"/")

      if (tmpDir.exists()) {
         tmpDir.setWritable(true)
         Utilities.deleteDirs(tmpDir)         
         tmpDir.delete()
         assertFalse(tmpDir.exists())
      }

      // Test for file not found...
      try {
         boolean retStat = SCM.scmClone(ConfigPropertiesConstants.SCMGIT,scmURI,tmpDir,null,true)
         assertTrue(false)
      } catch(FileNotFoundException ex) {
      } catch(Exception) {
         throw ex
      }

      assertTrue(tmpDir.mkdirs() && tmpDir.setWritable(true))
      StringBuffer output = new StringBuffer()
      boolean retStat = SCM.scmClone(ConfigPropertiesConstants.SCMGIT,scmURI,tmpDir,output,true)
      assertTrue(retStat)
      output = null

      if (tmpDir.exists()) {
         retStat = true
         tmpDir.setWritable(true)
         Utilities.deleteDirs(tmpDir)         
         tmpDir.delete()
         assertFalse(tmpDir.exists())
      }
      assertTrue(retStat)
   }

   /**
    * Unit test for git cloning with target and user/password
    */
   void testScmGitCloneWithTargetAndUser() {
      if (runUnitTestsOnly()) {
        return;
      }
      String scmURI = map.get("git_repoURI")
      File   tempDir = this.getTmpDir()
      File tmpDir = new File(tempDir.getCanonicalPath()+File.separator+map.get("git_repoDir")+"/")

      if (tmpDir.exists()) {
         tmpDir.setWritable(true)
         Utilities.deleteDirs(tmpDir)         
         tmpDir.delete()
         assertFalse(tmpDir.exists())
      }

      // As this is a public URI, it will not matter what users you throw at it...
      String user = map.get("git_repoUser")
      String pwd  = map.get("git_repoUserPwd")

      // Test for file not found...
      try {
         boolean retStat = SCM.scmClone(ConfigPropertiesConstants.SCMGIT,scmURI,
                                    user,pwd,tmpDir)
         assertTrue(false)
      } catch(FileNotFoundException ex) {
      } catch(Exception) {
         throw ex
      }

      assertTrue(tmpDir.mkdirs() && tmpDir.setWritable(true))
      boolean retStat = SCM.scmClone(ConfigPropertiesConstants.SCMGIT,scmURI,
                                    user,pwd,tmpDir)
      assertTrue(retStat)

      if (tmpDir.exists()) {
         retStat = true
         tmpDir.setWritable(true)
         Utilities.deleteDirs(tmpDir)         
         tmpDir.delete()
         assertFalse(tmpDir.exists())
      }
      assertTrue(retStat)
   }

   /**
    * Unit test for git cloning with target and user/password
    */
   void testScmGitCloneWithTargetAndUserTrue() {
      if (runUnitTestsOnly()) {
        return;
      }
      String scmURI = map.get("git_repoURI")
      File   tempDir = this.getTmpDir()
      File tmpDir = new File(tempDir.getCanonicalPath()+File.separator+map.get("git_repoDir")+"/")

      if (tmpDir.exists()) {
         tmpDir.setWritable(true)
         Utilities.deleteDirs(tmpDir)         
         tmpDir.delete()
         assertFalse(tmpDir.exists())
      }

      // As this is a public URI, it will not matter what users you throw at it...
      String user = map.get("git_repoUser")
      String pwd  = map.get("git_repoUserPwd")

      // Test for file not found...
      try {
         boolean retStat = SCM.scmClone(ConfigPropertiesConstants.SCMGIT,scmURI,
                                    user,pwd,tmpDir,null,true)
         assertTrue(false)
      } catch(FileNotFoundException ex) {
      } catch(Exception) {
         throw ex
      }

      assertTrue(tmpDir.mkdirs() && tmpDir.setWritable(true))
      boolean retStat = SCM.scmClone(ConfigPropertiesConstants.SCMGIT,scmURI,
                                    user,pwd,tmpDir,null,true)
      assertTrue(retStat)

      if (tmpDir.exists()) {
         retStat = true
         tmpDir.setWritable(true)
         Utilities.deleteDirs(tmpDir)         
         tmpDir.delete()
         assertFalse(tmpDir.exists())
      }
      assertTrue(retStat)
   }
}