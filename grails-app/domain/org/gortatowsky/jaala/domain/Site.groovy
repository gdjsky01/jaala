package org.gortatowsky.jaala.domain

class Site {

    Double latitude
    Double longitude
    String name
    Integer altitudeInMeters

    static constraints = {
        name size: 1..100, blank: false, unique: true, nullable:false
        latitude matches: /[\d]+/, range: -90.0..90.0, nullable:true
        longitude matches: /[\d]+/, range: -90.0..90.0, nullable:true
        altitudeInMeters matches: /[\d]+/, range: -250..9000, nullable:true
    }
}
