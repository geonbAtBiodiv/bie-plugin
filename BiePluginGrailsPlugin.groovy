import grails.util.Environment
import au.org.ala.bie.ExtendedPluginAwareResourceBundleMessageSource

class BiePluginGrailsPlugin {

    // the plugin version
    def version = "1.3.0-SNAPSHOT"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.4 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    def title = "Bie Plugin Plugin" // Headline display name of the plugin
    def author = "Dave Martin"
    def authorEmail = "david.martin@csiro.au"
    def description = '''\
A plugin providing basic species page functionality and site search.
'''
    // URL to the plugin's documentation
    def documentation = "https://github.com/atlasoflivingaustralia/bie-plugin"

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "MPL1.1"

    // Details of company behind the plugin (if there is one)
    def organization = [ name: "Atlas of Living Australia", url: "http://www.ala.org.au/" ]

    // Any additional developers beyond the author specified above.
    def developers = [ [ name: "Dave Martin", email: "david.martin@csiro.au" ]]

    // Location of the plugin's issue tracker.
    def issueManagement = [ system: "GitHub", url: "http://github.com/AtlasOfLivingAustralia/bie-plugin/issues" ]

    // Online location of the plugin's browse-able source code.
    def scm = [ url: "git://github.com/AtlasOfLivingAustralia/bie-plugin" ]

    def doWithSpring = {
        def config = application.config
        def loadConfig = new ConfigSlurper(Environment.current.name).parse(application.classLoader.loadClass("defaultConfig"))
        application.config = loadConfig.merge(config) // client app will now override the defaultConfig version

        // Custom message source
        messageSource(ExtendedPluginAwareResourceBundleMessageSource) {
            basenames = ["WEB-INF/grails-app/i18n/messages"] as String[]
            cacheSeconds = (60 * 60 * 6) // 6 hours
            useCodeAsDefaultMessage = false
        }
    }
}