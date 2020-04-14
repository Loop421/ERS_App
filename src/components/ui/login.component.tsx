import React, { useState, useEffect } from 'react';
import Axios from 'axios';
import{History} from 'history';

interface LoginComponentProps{
    history?: History;
}

const LoginComponent: React.FC<LoginComponentProps> = (props) =>
{
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');   
 
    const hanldeUsernameUpdate = (e: React.ChangeEvent<HTMLInputElement>) =>
    {
        const enteredUsername = e.target.value;
        setUsername(enteredUsername);
    }

    // localStorage.getItem('id'); this is how to fetch from local
    
    const handlePassword = (e: React.ChangeEvent<HTMLInputElement>) =>
    {
        const enteredPassword = e.target.value;
        setPassword(enteredPassword);
    }
    
    const updateGlobalID = () => {
        Axios.post('http://localhost:8080/Project1_war/api/employees/id',{
            username,
            password
        }).then((response) =>{

            localStorage.setItem('id', response.data); // this is how to store in local

        }).catch((err) =>{
            console.log(err)
        });
        
    }
    
    const submitClick = ( username: string, password: string) => {
        // console.log("Login");

        Axios.post('http://localhost:8080/Project1_war/api/employees/',{

            username,
            password
        }).then((response) =>{
            updateGlobalID();
            if(response.data === "employee")
            {
               props.history?.push("/welcome");
            }
            else if(response.data === "manager")
            {
                props.history?.push('/manager');
            }
            else
            {
                props.history?.push('/login');
                
            }
        }).catch((err) =>{
            console.log(err);
        });
    }
    return(
    <div className="container">
            <form>
                
                <div className="form-group">
                    <label htmlFor="username">Username:</label>
                    <input 
                        type="username" 
                        className="form-control"
                        id="username"
                        placeholder="Enter username"
                        name="username"
                        value={username}
                        required
                        onChange={(e) => hanldeUsernameUpdate(e)}
                    ></input>
                </div>
                <div className="form-group">
                    <label htmlFor="pwd">Password:</label>
                    <input 
                        type="password" 
                        className="form-control" 
                        id="pwd"
                        placeholder="Enter password"
                        name="password"
                        value={password}
                        onChange={(e) => handlePassword(e)}
                        />
                </div>
                <button 
                    onClick={(evt) => {evt.preventDefault(); 
                        submitClick( username, password)}} 
                    
                    className="btn btn-default">
                Submit</button>
            </form>
            
    </div>
      
    )
}

export default LoginComponent;