import React, { useState } from 'react';
import { Switch, Route, Redirect, BrowserRouter } from 'react-router-dom';
import WelcomeComponent from '../content/welcome.component';
import ProfileComponent from '../content/profile.component';
import ReimbursementComponent from '../content/reimbursement.component';
import LoginComponent from './login.component';
import ManagerPageComponent from '../content/managerpage.component';
import EmployeesComponent from '../content/empolyees.component';
import PendongReimbursementComponent from '../content/pending-reimbursement.component';
import ApproveReimbursementComponent from '../content/approve-reimbursement.component';
import EmployeeReimbursementComponet from '../content/employee-reimbursement.component';




const ContentComponent: React.FC = () =>
{
    const [reimbursment, setReimbursement] = useState([]);

    const updateReimbursemnt = (set: any): void =>
    {
        setReimbursement(set);
    } 
    
    return(
        <section id="content-area">

            <Switch>
                <Route path="/welcome" component={WelcomeComponent}></Route>
                <Route path="/profile" component={ProfileComponent}></Route>

                <Route path="/reimbursement" component={ReimbursementComponent}></Route>
                <Route path="/pending" component={PendongReimbursementComponent}></Route>
                <Route path="/approve" component={ApproveReimbursementComponent}></Route>
                <Route path="/empReim" component={EmployeeReimbursementComponet}></Route>

                <Route path="/manager" component={ManagerPageComponent}></Route>
                <Route path="/allemployess" component={EmployeesComponent}></Route>
                <Route path="/" component={LoginComponent}></Route>
                {/* <Route exact path="/" render={props => <LoginComponent  {...props} updateNum={(set: number) => updateNum(set)} />}></Route> */}
            </Switch>
        </section>
    )
}



export default ContentComponent;