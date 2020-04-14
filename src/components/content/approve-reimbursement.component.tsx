import React, { useState, useEffect } from 'react';
import {BootstrapTable,
        TableHeaderColumn} from 'react-bootstrap-table';
import '../../css/Table.css';
import '../../../node_modules/react-bootstrap-table/css/react-bootstrap-table.css';
import InputComponent from '../ui/input.component';
import Axios from 'axios';
import ManNavComponent from '../ui/mannav.component';
import TableComponent from './table.component';
  

interface ApproveReimbComponentProps
{
    history: any
}

const ApproveReimbursementComponent: React.FC<ApproveReimbComponentProps> = (props) =>
{

    const [reimbursment, setReimbursement] = useState([]);

    useEffect(()=>{
        Axios.get(`http://localhost:8080/Project1_war/api/reimbursements/approve`)
            .then(response =>{
                setReimbursement(response.data);
            }).catch((err) =>{
                console.log(err);
            })
    },[]);

    const submitClickForPending = () =>{
        props.history.push('/pending')
    }

    const submitClickForReim = () =>{
        props.history.push('/reimbursement')
    }
    
    return(
        
        <React.Fragment>
            <ManNavComponent></ManNavComponent>
            <h2>Expense Reimbursements</h2>
            <hr/>
            <br/>
            <div className='table'>
                <TableComponent reimbursment={reimbursment}></TableComponent>
            </div>
            <div className="btn-container">
                <button 
                    onClick={submitClickForReim}
                    className="btn btn-default"
                >All</button>
                <button 
                    onClick={submitClickForPending}
                    className="btn btn-default"
                >Pending</button>
            </div>
        </React.Fragment>
    )
}


export default ApproveReimbursementComponent;