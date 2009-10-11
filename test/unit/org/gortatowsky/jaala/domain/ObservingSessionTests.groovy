package org.gortatowsky.jaala.domain

import grails.test.*
import junit.framework.*
import grails.converters.JSON
import groovy.io.GroovyPrintWriter
import groovy.sql.DataSet

class ObservingSessionTests extends GrailsUnitTestCase {

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {

        super.tearDown()
    }

    void testCreateObservingSession() {

        mockDomain(ObservingSession)
        ObservingSession os = new ObservingSession()
        assertFalse "new object is not valid yet", os.validate()
        ObservingSession os1 = new ObservingSession(sessionDate: new Date())
        assertFalse "new object is valid", os1.validate()
        ObservingSession os2 = new ObservingSession(sessionDate: new Date(), observingSite: new Site())
        assertTrue "new object with site is valid", os2.validate()

    }
}
