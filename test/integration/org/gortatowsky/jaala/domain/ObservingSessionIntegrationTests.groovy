package org.gortatowsky.jaala.domain

import grails.test.*

class ObservingSessionIntegrationTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreateObservingSession() {

        ObservingSession os = new ObservingSession()
        assertTrue "Validation should fail", os.validate()
        assertTrue os.hasErrors()
        def errors = os.errors
        println errors.inspect()

    }
}
