package com.andhiratobing.nbr.data.remote.mapper

import com.andhiratobing.nbr.data.model.User
import com.andhiratobing.nbr.data.remote.response.UserResponse
import com.andhiratobing.nbr.util.Mapper
import javax.inject.Inject

class RemoteMapper @Inject constructor(
) : Mapper<UserResponse, User> {

    override fun mapFromModel(model: UserResponse): User {
        return User(
            id = model.id,
            username = model.username,
            avatarUser = model.avatarUser,
            gender = model.gender
        )
    }

    override fun mapToModel(domainModel: User): UserResponse {
        return UserResponse(
            id = domainModel.id,
            username = domainModel.username,
            avatarUser = domainModel.avatarUser,
            gender = domainModel.gender
        )
    }

    fun mapFromModelList(model: List<UserResponse>): List<User> {
        return model.map { mapFromModel(it) }
    }


}


