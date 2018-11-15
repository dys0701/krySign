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
                User("dengyusheng_cd@keruyun.com","邓毓生", "dys341112690","03055A7F")
                ,User("zhangwei_cd@keruyun.com","张伟", "Zw915611","099517F-34Y2-BFFD-997NAYW")
                ,User("zhujiapei_cd@keruyun.com","朱佳珮", "123456","0FSDG-34Y2-SDFF-XXDS")
                ,User("lixinyu_cd@keruyun.com","李昕宇", "adgj2012","099517F-3432-1FFD-9C1091712")
                ,User("pul@keruyun.com","蒲林", "plly1987","A0000060A830CC")
                ,User("baol@keruyun.com","包磊", "bao198920","B9865B2B")
                ,User("panw@keruyun.com","潘伟", "19851128","66785A7F-1132-9GFD-9D20-X0864FA")
                ,User("zhangpeng_cd@keruyun.com","张鹏", "Fade2black","998XT5A7F-7785-90FX-9T04-G0864BFG52POM")
                ,User("tanjinghui_cd@keruyun.com","谭警官", "Keruyun123","996XT5A7F")
                ,User("chenjialu_cd@keruyun.com","陈佳璐", "Lu123456","09785A7F-1432-4FFD-9C10-96452MU")
                ,User("mojingtian_cd@keruyun.com","莫敬添", "Mojt1024","09785A7F-1432-4FFD-9C1091712")
                ,User("13550017615","胡海", "huhai007","099517F-3432-1FFD-999NAYW")
                ,User("liuyi_cd@keruyun.com","刘怡", "19950715Ly","735517F-31Q2-DNFC-88TTCW")
                ,User("15828096204","何磊", "Helei123","222AS-SJYW1-11ZE-PIN2")
                ,User("zhanglei_cd@keruyun.com","张蕾", "Zloveleino001","220QX-SJYW1-11ZE-1I122")
                ,User("liuyi_cd@keruyun.com","刘怡", "19950715Ly","735517F-31Q2-DNFC-88TTCW")
                ,User("18384775915","向楠", "suadiahi@1.","FJSIOGOI-31Q2-DNFC-OOLJ")
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