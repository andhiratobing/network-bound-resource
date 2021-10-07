package com.andhiratobing.nbr.data.local.mapper

import com.andhiratobing.nbr.data.local.entities.UserEntity
import com.andhiratobing.nbr.data.model.User
import com.andhiratobing.nbr.util.Mapper
import javax.inject.Inject

class LocalMapper @Inject constructor() : Mapper<UserEntity, User> {

    override fun mapFromModel(model: UserEntity): User {
        return User(
            id = model.id,
            username = model.username,
            avatarUser = model.avatarUser,
            gender = model.gender
        )
    }

    override fun mapToModel(domainModel: User): UserEntity {
        return UserEntity(
            id = domainModel.id,
            username = domainModel.username,
            avatarUser = domainModel.avatarUser,
            gender = domainModel.gender
        )
    }

    fun mapFromModelList(model: List<UserEntity>): List<User> {
        return model.map { mapFromModel(it) }
    }


}