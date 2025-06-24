import { useEffect, useState } from "react"
import axios from "axios"
import UserModal from "./UserModal"
import { API_ENDPOINT } from "../API_ENDPOINT"
const UserList = () => {
    const [userArr, setUserArr] = useState([])
    const [showModal, setShowModal] = useState(false)
    const [editedUser, seteditedUser] = useState(null)
    const [msg, setMsg] = useState("")

    const fetchUser = async () => {
        try {
            const res = await axios.get(`${API_ENDPOINT}/users`)
            setUserArr(res.data)
        } catch (error) {
            console.error("Error getinig users")
        }
    }

    const handleEdit = (user) => {
        seteditedUser(user)
        setShowModal(true)
    }

    const handleAdd = () => {
        seteditedUser(null)
        setShowModal(true)
    }

    const handleDelete = async (userId) => {
        if (window.confirm("Confirm delete?")) {
            try {
                await axios.delete(`${API_ENDPOINT}/users/${userId}`, {
                    headers: {
                        Authorization: "Bearer " + localStorage.getItem("token")
                    }
                })
                //filtering deleted user  
                // or can call fetchUser again
                setUserArr(userArr.filter(user => user.id !== userId))
                setMsg("User Deleted")
            } catch (error) {
                setMsg(error.response.data.message)
            }
        }
    }

    const modalActions = async (userData) => {
        try {
            if (editedUser) {
                const res = await axios.put
                    (`${API_ENDPOINT}/users/${editedUser.id}`,
                        userData,
                        {
                            headers: {
                                Authorization: "Bearer " + localStorage.getItem("token")
                            }
                        }
                    )
                setMsg("User updated Successfully")
                const updated = userArr.map(user => user.id === editedUser.id ? res.data : user)
                setUserArr(updated)
            } else {
                const res = await axios.post(
                    `https://gorest.co.in/public/v2/users`,
                    userData,
                    {
                        headers: {
                            Authorization: "Bearer " + localStorage.getItem("token")
                        }
                    }
                )
                setMsg("User Added Successfully")
                setUserArr([...userArr, res.data])
            }
            // fetchUser()
        } catch (error) {
            setMsg(error.response.data[0].field + " " + error.response.data[0].message || error.response.data.message)
        }
        finally {
            setShowModal(false)

        }
    }

    useEffect(() => {
        fetchUser()
    }, [])

    return (
        <div className="container mt-4">
            <div className="d-flex justify-content-between align-items-center mb-2">
                <h2>User List</h2>
                <button className="btn btn-success" onClick={handleAdd}>Add User</button>
            </div>
            {msg && <p className="alert alert-success" role="alert" >{msg}</p>}
            <table className="table table-bordered">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Gender</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {userArr.map(user => (
                        <tr key={user.id}>
                            <td>{user.name}</td>
                            <td>{user.email}</td>
                            <td>{user.gender}</td>
                            <td>{user.status}</td>
                            <td>
                                <button
                                    className="btn btn-sm btn-primary me-2"
                                    onClick={() => handleEdit(user)}
                                >
                                    Edit
                                </button>
                                <button
                                    className="btn btn-sm btn-danger"
                                    onClick={() => handleDelete(user.id)}
                                >
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            {showModal &&
                <UserModal
                    onClose={() => setShowModal(false)}
                    onSubmit={modalActions}
                    editedUser={editedUser}
                />
            }
        </div>
    )
}

export default UserList
