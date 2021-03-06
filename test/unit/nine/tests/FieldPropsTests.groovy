package nine.tests
import grails.test.mixin.*
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.builder.AstBuilder;
import org.junit.*
import gorm.*


class FieldPropsTests {

	@Test
    void test_buildFiledPropsMap() {
		ConfigObject co = new ConfigSlurper().parse(testConfig);
		assert co.grails.plugin.audittrail
		assert FieldProps.getMap(co, "grails.plugin.audittrail.createdBy")
		assert !FieldProps.getMap(co, "grails.plugin.audittrail.xxx")
		//assert co.getProperty("grails.plugin.audittrail")
		def fmap = FieldProps.buildFieldMap(co)
		assert fmap
		assert fmap.get("createdBy")
		assert fmap.get("createdBy").name == "blah"
		assert fmap.get("createdBy").type == Long
		
		assert fmap.get("createdDate") == null
		//FieldProps.buildFieldMap()
    }

def testConfig ="""
	grails{
		plugin{
			audittrail{			
				//this should have all the defaults
				createdBy.field   = "blah" 
			
				editedBy.type   = "java.lang.String" 
				editedBy.constraints = "nullable:true,bindable:false"
			
				editedDate.type  = "java.util.Date" 
				editedDate.mapping = "type: org.jadira.usertype.dateandtime.joda.PersistentDateTime"
			
				currentUserClosure = {ctx->
					return "RON"
				}
			}
		}
	}

"""
}
