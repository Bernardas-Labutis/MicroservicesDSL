import "./App.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import { codelabToolbox, codelabGenerator } from "./customBlocks/custom_Blocks";
import React, { useState, useEffect } from "react";
import { BlocklyWorkspace } from "react-blockly";
import Blockly from "blockly";
import Button from 'react-bootstrap/Button';
import Alert from 'react-bootstrap/Alert'
import { BsDownload } from 'react-icons/bs'
import axios from "axios";

export default function App() {
  const [xml, setXml] = useState("");
  const [javascriptCode, setJavascriptCode] = useState("");
  const [show, setShow] = useState(false);

  const initialXml =
    '<xml xmlns="http://www.w3.org/1999/xhtml"><block type="text" x="70" y="30"><field name="TEXT"></field></block></xml>';

  function workspaceDidChange(workspace) {
    const code = codelabGenerator.workspaceToCode(workspace);
    setJavascriptCode(code);
  }

  return (
    <>
      <AlertDismissibleExample />
      <BlocklyWorkspace
        toolboxConfiguration={codelabToolbox}
        initialXml={initialXml}
        className="fill-height"
        workspaceConfiguration={{
          grid: {
            spacing: 20,
            length: 3,
            colour: "#ccc",
            snap: true,
          },
        }}
        onWorkspaceChange={workspaceDidChange}
        onXmlChange={setXml}
      />
      {/* {<textarea
        id="code"
        style={{ height: "200px", width: "400px" }}
        value={javascriptCode}
        readOnly
      ></textarea>} */}
      <LoadingButton />
    </>
  );

  function generate() {
    return axios.post("http://localhost:9099/generateZip", javascriptCode, {
      responseType: 'arraybuffer',
      headers: {
        // Overwrite Axios's automatically set Content-Type
        'Content-Type': 'application/json'
      }
    })
      .then(response => {
        console.log(response);

        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', "generated.zip");
        document.body.appendChild(link);
        link.click();
        link.remove();// you need to remove that elelment which is created before.)
      })
      .catch(exception => setShow(true));


  }

  function AlertDismissibleExample() {
    if (show) {
      return (
        <Alert variant="danger" onClose={() => setShow(false)} dismissible>
          <Alert.Heading>Could not generate project</Alert.Heading>
          <p>
            A microservice architecture should have a registry, gateway, at least one service with a communication api.
            Please check your blocks and try again.
          </p>
        </Alert>
      );
    } else { return "" }
  }

  function LoadingButton() {
    const [isLoading, setLoading] = useState(false);

    useEffect(() => {
      if (isLoading) {
        generate().then(() => {
          setLoading(false);
        });
      }
    }, [isLoading]);

    const handleClick = () => setLoading(true);

    return (
      <Button
        variant="primary"
        size="lg"
        disabled={isLoading}
        onClick={!isLoading ? handleClick : null}
      >
        {" "}<BsDownload />
        {isLoading ? ' Loading…' : ' Generate and download project'}
      </Button >
    );
  }
}
