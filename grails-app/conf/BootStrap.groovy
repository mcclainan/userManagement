import grails.util.Environment
import org.macsuite.usermanagement.Role
import org.macsuite.usermanagement.User
import org.macsuite.usermanagement.UserRole

class BootStrap {

    def init = { servletContext ->
        switch (Environment.current){
            case Environment.DEVELOPMENT:
                createAdmin()
                createUser()
                break
            case Environment.TEST:
                createAdmin()
                break
            case Environment.PRODUCTION:
                createAdmin()
                break
        }
        createAdmin()
    }

    def createAdmin(){
        def adminRole = Role.findByAuthority('ROLE_ADMIN')?:new Role(authority:'ROLE_ADMIN').save(failOnError:true)
        def user = User.findByUsername('super')?:new User(username:'super',firstName: "Admin", lastName: 'User', email: 'mcclainan@gmail.com', enabled:true,password:'Love@shine1star').save(failOnError:true)
        if(!user.authorities.contains(adminRole)){
            UserRole.create(user,adminRole,true)
        }
    }

    def createUser(){
        def userRole = Role.findByAuthority('ROLE_USER')?:new Role(authority:'ROLE_USER').save(failOnError:true)
        def user = User.findByUsername('standard')?:new User(username:'standard',firstName: "Standard", lastName: 'User', email: 'email@gmail.com', enabled:true,password:'password').save(failOnError:true)
        if(!user.authorities.contains(userRole)){
            UserRole.create(user,userRole,true)
        }
    }

    def destroy = {
    }
}
