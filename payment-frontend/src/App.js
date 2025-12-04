import { useState } from "react";

function App() {
  const [customerId, setCustomerId] = useState("");
  const [provider, setProvider] = useState("BANKA");
  const [amount, setAmount] = useState("");
  const [result, setResult] = useState(null);

  const handlePay = async () => {
    const response = await fetch("http://localhost:8080/api/payments", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        customerId: customerId,
        providerCode: provider,
        amount: parseFloat(amount),
      }),
    });

    const data = await response.json();
    setResult(data);
  };

  return (
    <div style={{ padding: "20px", maxWidth: "400px", fontFamily: "Arial" }}>
      <h2>Payment Switch</h2>

      <label>Customer ID:</label>
      <input
        value={customerId}
        onChange={(e) => setCustomerId(e.target.value)}
      />

      <br /><br />

      <label>Provider:</label>
      <select value={provider} onChange={(e) => setProvider(e.target.value)}>
        <option value="BANKA">Bank A</option>
        <option value="BANKB">Bank B</option>
        <option value="WALLET">Wallet</option>
      </select>

      <br /><br />

      <label>Amount:</label>
      <input value={amount} onChange={(e) => setAmount(e.target.value)} />

      <br /><br />

      <button onClick={handlePay}>Submit Payment</button>

      <br /><br />

      {result && (
        <div>
          <h3>Result:</h3>
          <p>Status: {result.status}</p>
          <p>Message: {result.message}</p>
        </div>
      )}
    </div>
  );
}

export default App;
