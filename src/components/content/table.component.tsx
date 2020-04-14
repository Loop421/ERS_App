import React from 'react';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';

interface TableComponentProps
{
    reimbursment: any;
}

const TableComponent: React.FC<TableComponentProps> = (props) =>
{
    return(
        <div className='table'>
                <p className='table-header'>Reimbursement Request</p>
                <BootstrapTable data={props.reimbursment}>
                    
                    <TableHeaderColumn isKey dataField='reimId'>
                        ID
                    </TableHeaderColumn>
                    <TableHeaderColumn dataField='expenseType'>
                        TYPE
                    </TableHeaderColumn>
                    <TableHeaderColumn dataField='totalAmount'>
                        TOTAL_AMOUNT
                    </TableHeaderColumn>
                    <TableHeaderColumn dataField='status'>
                        Status
                    </TableHeaderColumn>
                    <TableHeaderColumn dataField='createDate'>
                        CREATE_DATE
                    </TableHeaderColumn>
                    <TableHeaderColumn dataField='empid'>
                        EmployeeId
                    </TableHeaderColumn>
                    <TableHeaderColumn dataField='manid'>
                        ManagerId
                    </TableHeaderColumn>
                </BootstrapTable>
            </div>
    )
}

export default TableComponent;