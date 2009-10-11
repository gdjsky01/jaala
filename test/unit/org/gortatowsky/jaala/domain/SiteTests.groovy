package org.gortatowsky.jaala.domain

import grails.test.*

class SiteTests extends GrailsUnitTestCase {

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testNullSiteFails() {

        mockDomain(Site)
        Site os = new Site()
        os.validate()
        pErrors(os)
        assert os.hasErrors()
        assertEquals "nullable", os.errors.name

    }

    void testBlankSiteFails() {

        mockDomain(Site)
        Site os = new Site(name: "")
        os.validate()
        pErrors(os)
        assert os.hasErrors()
        assertEquals "blank", os.errors.name

    }

    void testNonBlankSiteNameOk() {

        mockDomain(Site)
        Site os = new Site(name: "Lake San Antonio, California, United States of America")
        Boolean isValid = os.validate();
        pErrors(os)
        assertTrue "Site with only name should be valid", isValid

    }

    void testNameTooLong() {

        mockDomain(Site)
        Site os = new Site(name: "1" * 101)
        os.validate();
        pErrors(os)
        assert os.hasErrors()
        assertEquals "size", os.errors.name
        os.name = "1" * 100
        os.validate()
        assert !os.hasErrors()
    }



    void testBadLatLong() {

        mockDomain(Site)
        Site os = new Site(name: "1" * 100)
        assert testLatLongAlt(os, "longitude", 180.0)
        assertEquals "range", os.errors.longitude
        assert !testLatLongAlt(os, "longitude", -90.0)
        assert !testLatLongAlt(os, "longitude", 90.0)
        assert !testLatLongAlt(os, "longitude", 0.0)

        assert testLatLongAlt(os, "latitude", 180.0)
        assertEquals "range", os.errors.latitude
        assert !testLatLongAlt(os, "latitude", -90.0)
        assert !testLatLongAlt(os, "latitude", 90.0)
        assert !testLatLongAlt(os, "latitude", 0.0)

        assert testLatLongAlt(os, "altitudeInMeters", -400)
        assertEquals "range", os.errors.altitudeInMeters
        assert testLatLongAlt(os, "altitudeInMeters", 22400)
        assertEquals "range", os.errors.altitudeInMeters
        assert !testLatLongAlt(os, "altitudeInMeters", 250)
        assert testLatLongAlt(os, "altitudeInMeters", 9001)

    }

    private Boolean testLatLongAlt(def os, def property, def amount) {

        os."${property}" = amount
        os.validate()
        pErrors(os)
        os.hasErrors()

    }
    private void pErrors(def thing) {

        if (thing?.metaClass.respondsTo(thing, "hasErrors")) {

            if (thing.hasErrors()) {

                log.info(thing.errors.toString())

            }
        }
    }
}
