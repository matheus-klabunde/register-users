import {useEffect, useRef, useState} from 'react'
import './style.css'
import Trash from '../../assets/trash.svg'
import api from '../../services/api'

function Home() {
    const [users, setUsers] = useState([])

    const inputName = useRef()
    const inputAge = useRef()
    const inputEmail = useRef()

    async function getUsers() {
        try {
            const usersFromApi = await api.get('/users')
            setUsers(usersFromApi.data)
        } catch (error) {
            console.error('Error fetching users:', error);
        }
    }

    async function registerUser() {
        try {
            await api.post('/user', {
                name: inputName.current?.value,
                age: inputAge.current?.value,
                email: inputEmail.current?.value
            })
            await getUsers()
        } catch (error) {
            if (error?.status === 409) {
                alert(error.response?.data)
            } else {
                console.log('Error removing user: ', error)
            }
        }
    }

    async function deleteUser(id) {
        try {
            await api.delete(`/user/${id}`).then(() => getUsers())
        } catch (error) {
            console.error('Error deleting user:', error);
        }
    }

    useEffect(() => {
        getUsers().then(() => console.log("Loaded users"))
    }, [])

    return (
        <div className='container'>
            <form>
                <h1>User Registration</h1>
                <input placeholder='Name' name='name' type='text' ref={inputName}/>
                <input placeholder='Age' name='age' type='number' ref={inputAge}/>
                <input placeholder='E-mail' name='email' type='email' ref={inputEmail}/>
                <button type='button' onClick={registerUser}>Register</button>
            </form>

            {users.map(user => (
                <div key={user.id} className='card'>
                    <div>
                        <p>Name: <span>{user.name}</span></p>
                        <p>Age: <span>{user.age}</span></p>
                        <p>E-mail: <span>{user.email}</span></p>
                    </div>
                    <button onClick={() => deleteUser(user.id)}>
                        <img src={Trash} alt="Delete user"/>
                    </button>
                </div>
            ))}
        </div>
    )
}

export default Home
