import "./App.css";
import { useState } from "react";
import Axios from "axios";

function App() {
  const [companyCEO, setCompanyCEO] = useState("");
  const [companyCode, setCompanyCode] = useState("");
  const [companyName, setCompanyName] = useState("");
  const [companyTurnover, setCompanyTurnover] = useState("");
  const [companyWebsite, setCompanyWebsite] = useState("");
  const [stockPrice, setStockPrice] = useState(0);
  /**/ 
  
  const [companies, setCompanies] = useState([]);


  const addCompany = () => {
    Axios.post("https://localhost:8284/api/v1.0/market/company/register", {
      companyCEO: companyCEO,
      companyCode: companyCode,
      companyName: companyName,
      companyTurnover: companyTurnover,
      companyWebsitege: companyWebsite,
      stockPrice: stockPrice,
    }).then(() => {
      setCompanies([
        ...companies,
        {
          companyCEO: companyCEO,
          companyCode: companyCode,
          companyName: companyName,
          companyTurnover: companyTurnover,
          companyWebsitege: companyWebsite,
          stockPrice: stockPrice,
        },
      ]);
    });
  };

  const getCompanies = () => {
    Axios.get("https://localhost:8284/api/v1.0/market/company/getall").then((response) => {
      setCompanies(response.data);
    });
  };


  const deleteCompany = (id) => {
    Axios.delete(`https://localhost:8284/api/v1.0/market/company/delete/${id}`).then((response) => {
      setCompanies(
        companies.filter((val) => {
          return val.id != id;
        })
      );
    });
  };

  return (
    <div className="App">
      <div className="information">
        <label>CompanyCEO:</label>
        <input
          type="text"
          onChange={(event) => {
            setCompanyCEO(event.target.value);
          }}
        />
         <label>CompanyCode:</label>
        <input
          type="text"
          onChange={(event) => {
            setCompanyCode(event.target.value);
          }}
        />

        <label>CompanyName:</label>
        <input
          type="text"
          onChange={(event) => {
            setCompanyName(event.target.value);
          }}
        />
        <label>CompanyTurnover:</label>
        <input
          type="text"
          onChange={(event) => {
            setCompanyTurnover(event.target.value);
          }}
        />

        <label>CompanyWebsite:</label>
        <input
          type="text"
          onChange={(event) => {
            setCompanyWebsite(event.target.value);
          }}
        />

        <label>StockPrice:</label>
        <input
          type="number"
          onChange={(event) => {
            setStockPrice(event.target.value);
          }}
        />
       
        <button onClick={addCompany}>Register Company</button>
      </div>
      <div className="companies">
        <button onClick={getCompanies}>Show Companies Registered</button>

        {companies.map((val, key) => {
          return (
            <div className="company" key={key}>
              <div>
                <h3>id: {val.id}</h3>
                <h3>companyCode: {val.companyCode}</h3>
                <h3>companyName: {val.companyName}</h3>
                <h3>companyCEO: {val.companyCEO}</h3>
                <h3>companyTurnover: {val.companyTurnover}</h3>
                <h3>companyWebsite: {val.companyWebsite}</h3>
                <h3>stockPrice: {val.stockPrice}</h3>
                
              </div>
              <div>
              
                <button
                  onClick={() => {
                    deleteCompany(val.id);
                  }}
                >
                  De-Register
                </button>
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default App;