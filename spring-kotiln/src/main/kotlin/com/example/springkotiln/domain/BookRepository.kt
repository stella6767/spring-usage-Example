package com.example.springkotiln.domain

import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book,Long>