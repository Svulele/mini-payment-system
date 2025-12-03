import { useState } from "react";
import axios from "axios";

function App() {
  const [customerId, setCustomerId] = useState("");
  const [provider, setProvider] = useState("BANKA");
  const [amount, setAmount] = useState("");
  const [response, setResponse] = useState(null);

  const submitPayment = async () => {
    try {
      const res = await axios.post("http://localhost:8080/api/payments", {
        customerId,
        provider,
        amount: Number(amount)
      });
      setResponse(res.data);
    } catch (err) {
      setResponse({ status: "FAILED", message: "Server error" });
    }
  };

  return (
    <div style={{ maxWidth: 400, margin: "50px auto", fontFamily: "Arial" }}>
      <h2>Payment Switch</h2>

      <input
        placeholder="Customer ID"
        value={customerId}
        onChange={e => setCustomerId(e.target.value)}
      />

      <select value={provider} onChange={e => setProvider(e.target.value)}>
        <option value="BANKA">Bank A</option>
        <option value="BANKB">Bank B</option>
        <option value="WALLET">Mobile Wallet</option>
      </select>

      <input
        type="number"
        placeholder="Amount"
        value={amount}
        onChange={e => setAmount(e.target.value)}
      />

      <button onClick={submitPayment}>Submit Payment</button>

      {response && (
        <div style={{ marginTop: 20 }}>
          <strong>{response.status}</strong>
          <p>{response.message}</p>
        </div>
      )}
    </div>
  );
}

export default App;
