package com.andhiratobing.nbr.util

interface Mapper<Model, DomainModel> {

    fun mapFromModel(model: Model): DomainModel

    fun mapToModel(domainModel: DomainModel): Model
}