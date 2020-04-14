import React, { useState } from 'react';
import Axios from 'axios';

interface InputComponentProps{
    updateEmployeeReim: (newValue: any) => void
}

const InputComponent: React.FC<InputComponentProps> = (props) =>
{
    const [expenseType, setExpenseType] = useState('');
    const [totalAmount, setTotalAmount] = useState('');
    const status: string = 'pending';
    const empid: any = localStorage.getItem('id');

    const handleType = (e: React.ChangeEvent<HTMLInputElement>) =>
    {
        const enterType = e.target.value;
        setExpenseType(enterType);
    }

    const handleAmountTotal = (e: React.ChangeEvent<HTMLInputElement>) =>
    {
        const enterAmount = e.target.value;
        setTotalAmount(enterAmount);
    }

    const submitClick = (type: string, total_amount: string) =>
    {
        Axios.post('http://localhost:8080/Project1_war/api/reimbursements/addExpense',{
            expenseType,
            totalAmount,
            status,
            empid
        }).then((response => {
            props.updateEmployeeReim(response.data)
        }));
    }

    return(
        <React.Fragment>
            <div className="input-container">
                <div className="from-group">
                    <label htmlFor="expenseType">Type:</label>
                    <input
                        type="expenseType"
                        className="form-control"
                        id="expenseType"
                        placeholder="Enter expense type"
                        name="expenseType"
                        value={expenseType}
                        onChange={(e) => handleType(e)}
                    ></input>
                    <br/>
                    <label htmlFor="Total_amount ">Total_amount :</label>
                    <input
                        type="totalAmount"
                        className="form-control"
                        id="totalAmount"
                        placeholder="Enter amount"
                        name="totalAmount"
                        value={totalAmount}
                        onChange={(e) => handleAmountTotal(e)}
                    ></input>
                    <br/>
                    <button
                        onClick={(evt) => {evt.preventDefault();
                        submitClick(expenseType, totalAmount)}}
                    >Submit</button>
                </div>
            </div>
        </React.Fragment>
    )
}


export default InputComponent;