import { createContext, ReactNode, useContext, useEffect, useState } from "react";

//First, we'll define Team and LoggedInUser Interfaces to structure our data
interface Team{
    teamId:string;
    teamName:string;
    teamLocation:string;
}

interface LoggedInUser{
    userId:string;
    username:string;
    role:string;
    team: Team;
    token:string
}

//define an AuthContextType interface to define initial state and a mutator for the user data
interface AuthContextType {
    loggedInUser: LoggedInUser | null;
    setLoggedInUser: (user: LoggedInUser | null) => void;
}

//use the AuthContextType above to create our AuthContext
const AuthContext = createContext<AuthContextType | null>(null) 

//The AuthProvider will PROVIDE our loggedInUser data to different components
//A Provider is a Component that we can wrap around other coponents to share state
//***See Main.tsx
export const AuthProvider: React.FC<{children:ReactNode}> = ({children}) => {

    //Define a useState for loggedInUser and we're local storage (not very secure!)
    const [loggedInUser, setLoggedInUser] = useState<LoggedInUser | null>(() => {
        const storedUser = localStorage.getItem('loggedInUser')
        return storedUser ? JSON.parse(storedUser) : null
    })

    //Extract the user info from localStorage on render/state change IF loggedInUser is truthy
    //If the loggedInUser doesn't exist yet (it isn't truthy) we can remove it
    useEffect(() => {
        if(loggedInUser) { 
            localStorage.setItem('loggedInUser', JSON.stringify(loggedInUser))
        } else {
            localStorage.removeItem('loggedInUser')
        }
    }, [loggedInUser])

    return (
        <AuthContext.Provider value={{loggedInUser, setLoggedInUser}}>
            {children}
        </AuthContext.Provider>
    )

}

//Finally, we'll define a hook that lets us access the User info in the AuthContext
//This will allows components to access the state, as long as its wrapped in the <AuthProvider>
export const useAuth = () : AuthContextType => {

    //extract the current context value, and check if it exists 
    //(it will exist if it's wrapped in the Provider)
    const context = useContext(AuthContext)

    if(!context) {
        throw new Error('useAuth must be used from within an AuthProvider')
    }

    return context

}