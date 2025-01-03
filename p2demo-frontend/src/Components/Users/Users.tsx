import axios from "axios"
import { useEffect, useState } from "react"
import { Button, Container, Table } from "react-bootstrap"
import { useNavigate } from "react-router-dom"
import { store } from "../../GlobalData/store"

//User Interface for type safety
interface User {
    userId:number,
    username:string,
    role:string,
    team:any //cutting a corner here, didn't wanna make a whole team interface :) should have made it global :p
}

export const Users:React.FC  = () => {

    //state object to store the User Array from the DB
    const [users, setUsers] = useState<User>([])

    //useNavigate hook
    const navigate = useNavigate()

    //useEffect to call the get request to get all users on component load
    useEffect(()=>{

        //check that the user is a "manager", otherwise route them back to login
        if(store.loggedInUser.role != "manager"){
            navigate("/")
        }

        getAllUsers()
    }, []) //[] so that this runs only once, when the component re-renders


    //Function to get all users 
    const getAllUsers = async () => {

        const response = await axios.get("http://localhost:4444/users", {
            headers: {
                'Authorization':`Bearer ${store.loggedInUser.token}`
            }
        })
        .then((response)=>{
            console.log(response)
            setUsers(response.data)
        })

    }


    return(
        <Container>

            <h3>Users:</h3>

            <Table className="table-primary table-hover">
                <thead className="table-dark">
                    <tr>
                        <th>User Id</th>
                        <th>Username</th>
                        <th>Role</th>
                        <th>Team Name</th>
                        <th>Options</th>
                    </tr>
                </thead>
                <tbody>
                    {users.map((user:User) => (
                        <tr>
                            <td>{user.userId}</td>
                            <td>{user.username}</td>
                            <td>{user.role}</td>
                            <td>{user.team.teamName}</td>
                            <td>
                                {/* Conditional Rendering - promote button if the user is a player, and demote button if the user is a manager */}
                                {user.role === "player" ? <Button>Promote</Button> : <Button className="btn-danger">Demote</Button>}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>

            {/* TODO: if no users, say that */}

        </Container>
    )

}