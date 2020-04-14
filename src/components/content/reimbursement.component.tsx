import React, { useState, useEffect } from 'react';
import '../../css/Table.css';
import '../../../node_modules/react-bootstrap-table/css/react-bootstrap-table.css';
import Axios from 'axios';
import ManNavComponent from '../ui/mannav.component';
import ManagerInputComponent from '../ui/manager-input.component';
import TableComponent from './table.component';

interface  ReimbursementComponentProps{
    history?: any;
}

const ReimbursementComponent: React.FC<ReimbursementComponentProps> = (props) =>
{
    
    
    
    const [reimbursement, setReimbursement] = useState([]);
    // const [reimArr, setReimArr] = useState([])
    // const componentDidMount= () =>
    // {
    //     Axios.get(`http://localhost:8080/Project1_war/api/reimbursements/`)
    //         .then(response =>{
    //             setReimbursement(response.data);
    //             console.log(response.data);
    //         }).catch((err) =>{
    //             console.log(err);
    //         })
            
    // }
    // const reimbursement: any = tempReimbursment;

    
    const updateReim = (newValue: any) =>
    {
        // props.updateReimbursemnt(newValue);
        setReimbursement(newValue);
    }
    

    

    useEffect(()=>{
        Axios.get(`http://localhost:8080/Project1_war/api/reimbursements/`)
            .then(response =>{
                setReimbursement(response.data);
                
            }).catch((err) =>{
                console.log(err);
            })
    },[]);

    const submitClickForPending = () =>{
        props.history?.push('/pending')
    }
    
    const submitClickForApprove = () =>{
        props.history?.push('/approve')
    }

    // componentDidMount();
    
    
    return(
        
        <React.Fragment>
            <ManNavComponent></ManNavComponent>
            <h2>Expense Reimbursements</h2>
            <hr/>
            <br/>
            <TableComponent 
                reimbursment={reimbursement}
            ></TableComponent>
            <div className="btn-container">
                <button 
                    onClick={submitClickForPending}
                    className="btn btn-default"
                >Pending</button>
                <button 
                    onClick={submitClickForApprove}
                    className="btn btn-default"
                >Approve</button>
            </div>
            <div>
                <ManagerInputComponent
                    updateReim={(newValue: any) => updateReim(newValue)}
                ></ManagerInputComponent>
            </div>
        </React.Fragment>
    )
}


export default ReimbursementComponent;