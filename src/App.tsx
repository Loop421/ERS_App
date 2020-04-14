import React, { useState } from 'react';

import './App.css';
import ContentComponent from './components/ui/content.component';
import { BrowserRouter} from 'react-router-dom';
import HeaderComponent from './components/ui/header.component';



const App: React.FC = () => {

  // const [data, setData] = useState<EmplData>({
  //   reimId: 1,
  //   expensetype: 'trave',
  //   totalamount: 100,
  //   status: 'pending',
  //   createdate: '12/4',
  //   empid: 1,
  //   manid: 2
  // });
  // const [id, setID] = useState(0)

  // const updateID = (num: number): void =>
  // {
  //   setID(num);
  // }

  // console.log(id)
  
  // updateID={(num: number) => updateID(num)}

  return (
    <div className="App">
      <BrowserRouter>
        <HeaderComponent></HeaderComponent>
        <br/>
        <br/>
        <br/>
        <ContentComponent></ContentComponent>
      </BrowserRouter>
      
    </div>
  );
}

export default App;
