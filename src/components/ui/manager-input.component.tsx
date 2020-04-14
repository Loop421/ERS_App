import React, { useState } from 'react';
import Axios from 'axios';

interface ManagerInputComponentProps{
    updateReim: (newValue: any) => void
}

const ManagerInputComponent: React.FC<ManagerInputComponentProps> = (props) =>
{
    const [status, setStatus] = useState('');
    const [reimId, setReimID] = useState('');
    const [manid, setManid] = useState('');

    const handleStatus = (e: React.ChangeEvent<HTMLInputElement>) =>
    {
        const enterStatus = e.target.value;
        setStatus(enterStatus);
    }

    const handleEmpID = (e: React.ChangeEvent<HTMLInputElement>) =>
    {
        const enterEmpID = e.target.value;
        setReimID(enterEmpID);
    }

    const handleManID = (e: React.ChangeEvent<HTMLInputElement>) =>
    {
        const enterManID = e.target.value;
        setManid(enterManID);
    }

    const submitClick = (status: string, reimId: string, manid: string) =>
    {
        Axios.post('http://localhost:8080/Project1_war/api/reimbursements/changeStatus',{
            status,
            reimId,
            manid
        }).then(response =>{
            props.updateReim(response.data);
        });
    }


    return(
        <React.Fragment>
            <div className="input-container">
                <div className="form-group">
                    <label htmlFor="status">Status</label>
                    <input 
                        type="status"
                        className="form-control"
                        id="status"
                        placeholder="Enter approve or deny"
                        name="status"
                        value={status}
                        onChange={(e) => handleStatus(e)}
                    ></input>
                    <br/>
                    <input 
                        type="reimId"
                        className="form-control"
                        id="reimId"
                        placeholder="Enter Reimbursement ID"
                        name="reimId"
                        value={reimId}
                        onChange={(e) => handleEmpID(e)}
                    ></input>
                    <br/>
                    <input 
                        type="manid"
                        className="form-control"
                        id="manid"
                        placeholder="Enter Your ID"
                        name="manid"
                        value={manid}
                        onChange={(e) => handleManID(e)}
                    ></input>
                    <button
                        onClick={(evt) => {evt.preventDefault();
                        submitClick(status, reimId, manid)}}
                    >Submit</button>
                </div>
            </div>
        </React.Fragment>
    )
}

export default ManagerInputComponent;
