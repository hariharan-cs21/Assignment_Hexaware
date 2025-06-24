import { useEffect, useState } from "react";

const UserModal = ({ onClose, onSubmit, editedUser }) => {
    const [inpData, setInpData] = useState({
        name: "",
        email: "",
        gender: "",
        status: "active",
    });

    useEffect(() => {
        if (editedUser) {
            setInpData(editedUser);
        } else {
            setInpData({
                name: "",
                email: "",
                gender: "",
                status: "active",
            });
        }
    }, [editedUser]);


    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(inpData);
    };


    return (
        <div className="modal show d-block" tabIndex="-1">
            <div className="modal-dialog">
                <div className="modal-content">
                    <form onSubmit={handleSubmit}>
                        <div className="modal-header">
                            <h5 className="modal-title">{editedUser ? "Edit" : "Add"} User</h5>
                        </div>
                        <div className="modal-body">
                            <div className="mb-3">
                                <label className="form-label">Name</label>
                                <input
                                    type="text"
                                    name="name"
                                    className="form-control"
                                    value={inpData.name}
                                    onChange={(e) => setInpData({ ...inpData, name: e.target.value })}
                                    required
                                />
                            </div>
                            <div className="mb-3">
                                <label className="form-label">Email</label>
                                <input
                                    type="email"
                                    name="email"
                                    className="form-control"
                                    value={inpData.email}
                                    onChange={(e) => setInpData({ ...inpData, email: e.target.value })}
                                    required
                                />
                            </div>
                            <div className="mb-3">
                                <label className="form-label d-block">Gender</label>
                                <div className="form-check form-check-inline">
                                    <input
                                        className="form-check-input"
                                        type="radio"
                                        name="gender"
                                        value="male"
                                        checked={inpData.gender === "male"}
                                        onChange={(e) => setInpData({ ...inpData, gender: e.target.value })}
                                        required
                                    />
                                    <label className="form-check-label">Male</label>
                                </div>
                                <div className="form-check form-check-inline">
                                    <input
                                        className="form-check-input"
                                        type="radio"
                                        name="gender"
                                        value="female"
                                        checked={inpData.gender === "female"}
                                        onChange={(e) => setInpData({ ...inpData, gender: e.target.value })}
                                        required
                                    />
                                    <label className="form-check-label">Female</label>
                                </div>
                            </div>
                            <div className="mb-3">
                                <label className="form-label">Status</label>
                                <select
                                    name="status"
                                    className="form-select"
                                    value={inpData.status}
                                    onChange={(e) => setInpData({ ...inpData, status: e.target.value })}
                                >
                                    <option value="active">Active</option>
                                    <option value="inactive">Inactive</option>
                                </select>
                            </div>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" onClick={onClose}>
                                Cancel
                            </button>
                            <button type="submit" className="btn btn-primary">
                                {editedUser ? "Update" : "Add"} User
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default UserModal;
