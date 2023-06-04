import React, { useState } from "react";
import {useNavigate} from "react-router-dom";
import "./Login.css";


const Login = () => {
    const [error, setError] = useState('');
    const navigate = useNavigate()
    const [data,setData] = useState({
        username : '',
        password : ''
    })

    const {username,password} = data;

    const handleChange = e => {
        e.preventDefault();
		setData({ ...data, [e.target.name]: e.target.value });
	};



    const formSubmitter = async (e) => {
        e.preventDefault();
    
        // Validate user ID
        if (!isValidUserId(username)) {
            setError('Please enter a valid user ID');
            return;
        }
    
        // Validate password
        if (!isValidPassword(password)) {
            setError('Password should have a minimum of 8 characters with a combination of uppercase, lowercase, numbers, and special characters');
            return;
        }
    
        try {
            const response = await fetch('http://localhost:8080/api/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ username, password }),
            });
    
            if (response.ok) {
                // Successful login
                navigate('/user')
                console.log('Login successful');
            } else {
                // Failed login
                setError('Invalid username or password');
            }
        } catch (error) {
            console.error('Error:', error);
            setError('An error occurred');
        }
    };
    
    const isValidUserId = (userId) => {
        return userId != null && userId.trim() !== '';
    };
    
    const isValidPassword = (password) => {
        return password != null && password.trim().length >= 8 && /[a-z]/.test(password) && /[A-Z]/.test(password) && /\d/.test(password) && /[!@#$%^&*]/.test(password);
    };

return (
    <div className="context">
        <div className="container">
            <div className='btn-group btn-group-lg d-flex gap-2' role="group" aria-label="...">
            <button type="button" className="btn btn-dark w-100 active">Home</button>
            <button type="button" className="btn btn-light w-100">Employees</button>
            <button type="button" className="btn btn-light w-100">Edit</button>
            <button type="button" className="btn btn-light w-100">Add</button>
            </div>
            <div className="form-login">
                <form className="login" onSubmit={formSubmitter}>
                    <span>Login</span><br />
                    {error && <div style={{ marginBottom: '10px', color: 'red' }} className="error">{error}</div>}
                    <label htmlFor="username" className="label">Username:</label>
                    <input type="text" name="username" placeholder="username" id="username" value={username} onChange={handleChange} />
                    <label htmlFor="password" className="label">Password:</label>
                    <input type="password" name="password" placeholder="password" id="password" value={password} onChange={handleChange}/><br />
                    <button className="text-decoration-none btn btn-sm btn-light" type="submit">Submit</button>
                </form>
            </div>
        </div>
    </div>
)

}

export default Login;