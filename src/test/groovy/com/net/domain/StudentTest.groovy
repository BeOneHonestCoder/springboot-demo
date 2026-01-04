package com.net.domain

import spock.lang.Specification

class StudentTest extends Specification {

    def "get ID"() {
        setup:
        def student = new Student()
        student.setId(1)

        when:
        def expectedId = student.getId()

        then:
        expectedId == 1
    }

    def "get multiple ID"() {
        given:
        def student = new Student()
        student.setId(id)
        def expectedId = student.getId()

        expect:
        expectedId == value

        where:
        id | value
        1  | 1
    }
}