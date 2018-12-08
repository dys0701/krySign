package com.keruyun.open.italent.presenter


import com.keruyun.open.italent.api.ItalentApi
import com.keruyun.open.italent.api.Sigin
import com.keruyun.open.italent.api.Token
import com.keruyun.open.italent.api.User
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainPresenter  @Inject constructor(private val api: ItalentApi){
    fun getUsers():List<User>{
        return listOf(
                
        )
    }

    fun getToken(user: User): Flowable<Token>{
        return api.getToken("100","password",false,user.pwd
                ,"16bd2f170d174be0aa58919568c113da",user.userName)
    }

    fun sigin(token: Token, user: User): Flowable<Sigin>{
        return api.sigin(token.access_token, token.tenant_id, token.user_id,user.device
                ,"30.540174,104.068521", "30.540174,104.068521", "70:3a:0e:a3:fe:40")
    }


}
