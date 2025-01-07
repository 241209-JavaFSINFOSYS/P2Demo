import React, { createContext, useState, ReactNode, useContext, useEffect } from 'react';

//First, we'll define Team and LoggedInUser interfaces to structure our data.

interface Team {
  teamId: number;
  teamName: string;
  teamLocation: string;
}

interface LoggedInUser {
  userId: number;
  username: string;
  role: string;
  team: Team;
  token: string;
}

//defining the loggedInUser's initial state, as well as a mutator (setter) for it
interface AuthContextType {
  loggedInUser: LoggedInUser | null;
  setLoggedInUser: (user: LoggedInUser | null) => void;
}

//use the AuthContextType above to create our AuthContext!
const AuthContext = createContext<AuthContextType | undefined>(undefined);

//The AuthProvider will PROVIDE our loggedInUser state to different components!
//A Provider is a Component that we can wrap around other components to share state
//***See Main.tsx
export const AuthProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  
    //Also, I'm gonna use localStorage so our logged-in state persists across refreshes
    const [loggedInUser, setLoggedInUser] = useState<LoggedInUser | null>(() => {
        const storedUser = localStorage.getItem('loggedInUser');
        return storedUser ? JSON.parse(storedUser) : null;
        });

    //Extract the user info on render if the loggedInUser is truthy. Otherwise remove it
    useEffect(() => {
        if (loggedInUser) {
            localStorage.setItem('loggedInUser', JSON.stringify(loggedInUser));
        } else {
            localStorage.removeItem('loggedInUser');
        }
        }, [loggedInUser]);

    return (
        <AuthContext.Provider value={{ loggedInUser, setLoggedInUser }}>
        {children}
        </AuthContext.Provider>
    );
};

//Finally, define a hook that lets us access the User state in the AuthContext
//This will allow components to access the state, IF it's wrapped in the <AuthProvider>
export const useAuth = (): AuthContextType => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};