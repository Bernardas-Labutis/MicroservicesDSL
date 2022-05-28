import Blockly from 'blockly';

Blockly.defineBlocksWithJsonArray([{
    "type": "object",
    "message0": "{ %1 %2 }",
    "args0": [{
        "type": "input_dummy"
    }, {
        "type": "input_statement",
        "name": "MEMBERS"
    }
    ],
    "output": null,
    "colour": 230,
}, {
    "type": "member",
    "message0": "%1 %2 %3",
    "args0": [{
        "type": "field_input",
        "name": "MEMBER_NAME",
        "text": ""
    }, {
        "type": "field_label",
        "name": "COLON",
        "text": ":"
    }, {
        "type": "input_value",
        "name": "MEMBER_VALUE"
    }
    ],
    "previousStatement": null,
    "nextStatement": null,
    "colour": 230,
}, {
    "type": "registry",
    "message0": "registry %1 port %2 services %3 gateway %4",
    "args0": [{
        "type": "input_dummy"
    }, {
        "type": "input_value",
        "name": "port",
        "check": "String",
        "align": "RIGHT"
    }, {
        "type": "input_statement",
        "name": "services",
        "check": "service",
        "align": "RIGHT"
    }, {
        "type": "input_value",
        "name": "gateway",
        "check": "gateway",
        "align": "RIGHT"
    }
    ],
    "inputsInline": false,
    "nextStatement": null,
    "colour": 165,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "service",
    "message0": "service %1 name %2 port %3 communication protocol %4 database %5 external calls %6",
    "args0": [{
        "type": "input_dummy"
    }, {
        "type": "input_value",
        "name": "name",
        "check": "String",
        "align": "RIGHT"
    }, {
        "type": "input_value",
        "name": "port",
        "check": "String",
        "align": "RIGHT"
    }, {
        "type": "input_value",
        "name": "communication_protocol",
        "check": "String",
        "align": "RIGHT"
    }, {
        "type": "input_value",
        "name": "database",
        "align": "RIGHT"
    }, {
        "type": "input_statement",
        "name": "external_calls",
        "align": "RIGHT"
    }
    ],
    "previousStatement": null,
    "nextStatement": null,
    "colour": 240,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "method",
    "message0": "method %1 type %2 %3 endpoint path %4 name %5 request %6 response %7",
    "args0": [{
        "type": "input_dummy"
    }, {
        "type": "field_dropdown",
        "name": "type",
        "options": [
            [
                "POST",
                "POST"
            ],
            [
                "GET",
                "GET"
            ],
            [
                "PUT",
                "PUT"
            ],
            [
                "DELETE",
                "DELETE"
            ]
        ]
    }, {
        "type": "input_dummy",
        "align": "RIGHT"
    }, {
        "type": "input_value",
        "name": "endpoint_path",
        "check": "path",
        "align": "RIGHT"
    }, {
        "type": "input_value",
        "name": "name",
        "check": "name",
        "align": "RIGHT"
    }, {
        "type": "input_value",
        "name": "request",
        //"check": /*["object",*/ "lists_create_with"/*],*/,
        "align": "RIGHT"
    }, {
        "type": "input_value",
        "name": "response",
        //"check": /*["object",*/ "lists_create_with"/*],*/,
        "align": "RIGHT"
    }
    ],
    "previousStatement": "method",
    "nextStatement": "method",
    "colour": 60,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "port",
    "message0": "port %1",
    "args0": [{
        "type": "field_input",
        "name": "port",
        "text": "8080"
    }
    ],
    "inputsInline": true,
    "output": null,
    "colour": 330,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "random",
    "message0": "",
    "colour": 230,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "name",
    "message0": "name %1",
    "args0": [{
        "type": "field_input",
        "name": "name",
        "text": "name"
    }
    ],
    "output": null,
    "colour": 330,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "path",
    "message0": "path %1 %2",
    "args0": [{
        "type": "field_input",
        "name": "path",
        "text": "/path"
    }, {
        "type": "input_value",
        "name": "path_parameter"
    }
    ],
    "inputsInline": false,
    "output": null,
    "colour": 330,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "path_parameter",
    "message0": "path parameter %1 %2",
    "args0": [{
        "type": "field_input",
        "name": "path_parameter",
        "text": "{id}"
    }, {
        "type": "input_value",
        "name": "path_parameter_next",
        "check": "path_parameter"
    }
    ],
    "output": null,
    "colour": 330,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "rest_api",
    "message0": "REST API %1 endpoint path %2 methods %3 tests %4",
    "args0": [{
        "type": "input_dummy"
    }, {
        "type": "input_value",
        "name": "path",
        "check": "path",
        "align": "RIGHT"
    }, {
        "type": "input_statement",
        "name": "methods",
        "check": "method",
        "align": "RIGHT"
    },
    {
        "type": "input_statement",
        "name": "tests",
        "align": "RIGHT"
    }
    ],
    "output": null,
    "colour": 120,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "database",
    "message0": "database %1 host %2 port %3 username %4 password %5 entities %6",
    "args0": [{
        "type": "input_dummy"
    }, {
        "type": "input_value",
        "name": "host",
        "align": "RIGHT"
    }, {
        "type": "input_value",
        "name": "port",
        "align": "RIGHT"
    }, {
        "type": "input_value",
        "name": "username",
        "align": "RIGHT"
    }, {
        "type": "input_value",
        "name": "password",
        "align": "RIGHT"
    }, {
        "type": "input_statement",
        "name": "entities",
        "align": "RIGHT"
    }
    ],
    "output": null,
    "colour": 30,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "host",
    "message0": "host %1",
    "args0": [{
        "type": "field_input",
        "name": "host",
        "text": "localhost"
    }
    ],
    "output": null,
    "colour": 330,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "username",
    "message0": "username %1",
    "args0": [{
        "type": "field_input",
        "name": "username",
        "text": "username"
    }
    ],
    "output": null,
    "colour": 330,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "password",
    "message0": "password %1",
    "args0": [{
        "type": "field_input",
        "name": "password",
        "text": "password"
    }
    ],
    "output": null,
    "colour": 330,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "field",
    "message0": "field %1 type %2 name %3",
    "args0": [{
        "type": "input_dummy"
    }, {
        "type": "input_value",
        "name": "type"
    }, {
        "type": "input_value",
        "name": "name"
    }
    ],
    "inputsInline": true,
    "previousStatement": null,
    "nextStatement": null,
    "colour": 230,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "entity",
    "message0": "entity %1 name %2 fields %3",
    "args0": [{
        "type": "input_dummy"
    }, {
        "type": "input_value",
        "name": "name",
        "align": "RIGHT"
    }, {
        "type": "input_statement",
        "name": "fields",
        "align": "RIGHT"
    }
    ],
    "previousStatement": [
        "entity",
        "database"
    ],
    "nextStatement": [
        "entity",
        "database"
    ],
    "colour": 30,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "type",
    "message0": "type %1",
    "args0": [{
        "type": "field_input",
        "name": "type",
        "text": "type"
    }
    ],
    "output": null,
    "colour": 330,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "gateway",
    "message0": "gateway %1 port %2 routes %3",
    "args0": [{
        "type": "input_dummy"
    }, {
        "type": "input_value",
        "name": "port",
        "align": "RIGHT"
    }, {
        "type": "input_statement",
        "name": "routes",
        "check": "route"
    }
    ],
    "output": "gateway",
    "colour": 195,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "route",
    "message0": "route %1 service name %2 path %3",
    "args0": [{
        "type": "input_dummy"
    },
    /*{
    "type": "field_dropdown",
    "name": "service_name",
    "options": [
    [
    "option",
    "OPTIONNAME"
    ],
    [
    "option",
    "OPTIONNAME"
    ],
    [
    "option",
    "OPTIONNAME"
    ]
    ]
    },*/
    {
        "type": "input_value",
        "align": "RIGHT",
        "name": "name",
        "check": "name"
    }, {
        "type": "input_value",
        "name": "path",
        "check": "path",
        "align": "RIGHT"
    }
    ],
    // "extensions": ["dynamic_menu_extension"],
    "inputsInline": true,
    "previousStatement": [
        "route",
        "routes"
    ],
    "nextStatement": [
        "route",
        "routes"
    ],
    "colour": 330,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "service_method_calls",
    "message0": "service method calls %1",
    "args0": [{
        "type": "input_statement",
        "name": "service_method_calls",
        "check": "service_method_call"
    }
    ],
    "previousStatement": [
        "method",
        "service_call"
    ],
    "nextStatement": [
        "method",
        "service_call"
    ],
    "colour": 230,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "rest_call",
    "message0": "REST call %1 type %2 %3 url %4 request %5 response %6",
    "args0": [{
        "type": "input_dummy"
    }, {
        "type": "field_dropdown",
        "name": "type",
        "options": [
            [
                "POST",
                "POST"
            ],
            [
                "GET",
                "GET"
            ],
            [
                "PUT",
                "PUT"
            ],
            [
                "DELETE",
                "DELETE"
            ]
        ]
    }, {
        "type": "input_dummy",
        "align": "RIGHT"
    }, {
        "type": "input_value",
        "name": "url",
        "check": "url",
        "align": "RIGHT"
    }, {
        "type": "input_value",
        "name": "request",
        "align": "RIGHT"
    }, {
        "type": "input_value",
        "name": "response",
        "align": "RIGHT"
    }
    ],
    "previousStatement": null,
    "nextStatement": null,
    "colour": 120,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "service_call",
    "message0": "service method calls %1",
    "args0": [{
        "type": "input_statement",
        "name": "service_method_calls",
        "check": "service_method_call"
    }
    ],
    "previousStatement": [
        "method",
        "service_call"
    ],
    "nextStatement": [
        "method",
        "service_call"
    ],
    "colour": 120,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "service_method_call",
    "message0": "service method call %1 service name %2 %3 method name %4",
    "args0": [{
        "type": "input_dummy"
    }, {
        "type": "field_dropdown",
        "name": "service_name",
        "options": [
            [
                "payment-service",
                "payment-service"
            ],
            [
                "authentication-service",
                "payment-service"
            ],
            [
                "option",
                "OPTIONNAME"
            ]
        ]
    }, {
        "type": "input_dummy"
    }, {
        "type": "field_dropdown",
        "name": "method_name",
        "options": [
            [
                "getToken",
                "getToken"
            ],
            [
                "pay",
                "pay"
            ],
            [
                "option",
                "OPTIONNAME"
            ]
        ]
    }
    ],
    "inputsInline": true,
    "previousStatement": [
        "service_method_calls",
        "service_method_call"
    ],
    "nextStatement": [
        "service_method_calls",
        "service_method_call"
    ],
    "colour": 120,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "url",
    "message0": "url %1",
    "args0": [{
        "type": "field_input",
        "name": "url",
        "text": "http://www.example.com/api/getUsers"
    }
    ],
    "output": "url",
    "colour": 330,
    "tooltip": "",
    "helpUrl": ""
}, {
    "type": "tracer",
    "message0": "tracer %1 host %2 port %3",
    "args0": [{
        "type": "input_dummy"
    }, {
        "type": "input_value",
        "name": "host",
        "check": "host",
        "align": "RIGHT"
    }, {
        "type": "input_value",
        "name": "port",
        "check": "port",
        "align": "RIGHT"
    }
    ],
    "previousStatement": null,
    "nextStatement": null,
    "colour": 0,
    "tooltip": "",
    "helpUrl": ""
},
{
    "type": "test_http_ok",
    "message0": "test_http_ok",
    "previousStatement": null,
    "nextStatement": null,
    "colour": 200,
    "tooltip": "",
    "helpUrl": ""
},
{
    "type": "test_methods",
    "message0": "test_methods",
    "previousStatement": null,
    "nextStatement": null,
    "colour": 200,
    "tooltip": "",
    "helpUrl": ""
}
]);

export let codelabToolbox = `
<xml xmlns="https://developers.google.com/blockly/xml" id="toolbox" style="display: none">
  <category name="registry">
	<block type="registry"/>
  </category>
  <category name="service">
  <block type="service"/>
  </category>
  <category name="communication">
	<block type="rest_api"/>
	<block type="method"/>
	<block type="gateway"/>
	<block type="rest_call"/>
	<block type="service_method_call"/>
	<block type="service_method_calls"/>
  </category>
  <category name="json">
  <block type="object"/>
	<block type="member"></block>
	<block type="math_number"><field name="NUM">0</field></block>
	<block type="text"><field name="TEXT"/></block>
	<block type="logic_boolean"><field name="BOOL">TRUE</field></block>
	<block type="logic_null"/>
	<block type="lists_create_with"><mutation items="3"/></block>
  </category>
  <category name="database">
	<block type="database"/>
	<block type="host"/>
	<block type="port"/>
	<block type="username"/>
	<block type="password"/>
	<block type="entity"/>
	<block type="field"/>
	<block type="type"/>
	<block type="name"/>
  </category>
  <category name="input">
	<block type="url"/>
	<block type="host"/>
	<block type="username"/>
	<block type="password"/>
	<block type="port"/>
	<block type="name"/>
	<block type="path"/>
	<block type="path_parameter"/>
	<block type="type"/>
	<block type="route"/>
  </category>
  <category name="tracer">
	<block type="tracer"/>
  </category>
  <category name="tests">
	<block type="test_http_ok"/>
    <block type="test_methods"/>
  </category>
</xml>
`

export let codelabGenerator = new Blockly.Generator('MICROLY');
codelabGenerator.PRECEDENCE = 0;

codelabGenerator['logic_null'] = function (block) {
    return ['null', codelabGenerator.PRECEDENCE];
};

codelabGenerator['text'] = function (block) {
    var textValue = block.getFieldValue('TEXT');
    var code = '"' + textValue + '"';
    return [code, codelabGenerator.PRECEDENCE];
};

codelabGenerator['math_number'] = function (block) {
    const code = Number(block.getFieldValue('NUM'));
    return [code, codelabGenerator.PRECEDENCE];
};

codelabGenerator['logic_boolean'] = function (block) {
    const code = (block.getFieldValue('BOOL') == 'TRUE') ? 'true' : 'false';
    return [code, codelabGenerator.PRECEDENCE];
};

codelabGenerator['member'] = function (block) {
    const name = block.getFieldValue('MEMBER_NAME');
    const value = codelabGenerator.valueToCode(block, 'MEMBER_VALUE',
        codelabGenerator.PRECEDENCE);
    const code = '"' + name + '": ' + value;
    return code;
};

codelabGenerator['lists_create_with'] = function (block) {
    const values = [];
    for (var i = 0; i < block.itemCount_; i++) {
        let valueCode = codelabGenerator.valueToCode(block, 'ADD' + i,
            codelabGenerator.PRECEDENCE);
        if (valueCode) {
            values.push(valueCode);
        }
    }
    const valueString = values.join(',\n');
    const indentedValueString =
        codelabGenerator.prefixLines(valueString, codelabGenerator.INDENT);
    const codeString = '[\n' + indentedValueString + '\n]';
    return [codeString, codelabGenerator.PRECEDENCE];
};

codelabGenerator['object'] = function (block) {
    const statement_members =
        codelabGenerator.statementToCode(block, 'MEMBERS');
    const code = '{\n' + statement_members + '\n}';
    return [code, codelabGenerator.PRECEDENCE];
};

codelabGenerator['port'] = function (block) {
    const code = '"port": ' + Number(block.getFieldValue('port'));
    return [code, codelabGenerator.PRECEDENCE];
};

codelabGenerator['name'] = function (block) {
    const code = '"name": ' + toJSONString(block.getFieldValue('name'));
    return [code, codelabGenerator.PRECEDENCE];
};

codelabGenerator['path'] = function (block) {
    const pathParameter = codelabGenerator.valueToCode(block, 'path_parameter', codelabGenerator.PRECEDENCE);
    const path = toJSONString(block.getFieldValue('path'));
    const code = toJSONProperty('"path"', path + pathParameter);
    return [code, codelabGenerator.PRECEDENCE];
};

codelabGenerator['path_parameter'] = function (block) {
    const parameter = codelabGenerator.valueToCode(block, 'path_parameter', codelabGenerator.PRECEDENCE);
    const next = codelabGenerator.valueToCode(block, 'path_parameter_next', codelabGenerator.PRECEDENCE);
    const code = parameter + next;
    return [code, codelabGenerator.PRECEDENCE];
};

codelabGenerator['host'] = function (block) {
    const code = toJSONProperty('"host"', toJSONString(block.getFieldValue('host')));
    return [code, codelabGenerator.PRECEDENCE];
};

codelabGenerator['username'] = function (block) {
    const code = toJSONProperty('"username"', toJSONString(block.getFieldValue('username')));
    return [code, codelabGenerator.PRECEDENCE];
};

codelabGenerator['password'] = function (block) {
    const code = toJSONProperty('"password"', toJSONString(block.getFieldValue('password')));
    return [code, codelabGenerator.PRECEDENCE];
};

codelabGenerator['type'] = function (block) {
    const code = toJSONProperty('"type"', toJSONString(block.getFieldValue('type')));
    return [code, codelabGenerator.PRECEDENCE];
};

codelabGenerator['url'] = function (block) {
    const code = toJSONProperty('"url"', toJSONString(block.getFieldValue('url')));
    return [code, codelabGenerator.PRECEDENCE];
};

var serviceNames = [];
codelabGenerator['service'] = function (block) {
    const name = codelabGenerator.valueToCode(block, 'name', codelabGenerator.PRECEDENCE);
    serviceNames.push(name);
    const port = codelabGenerator.valueToCode(block, 'port', codelabGenerator.PRECEDENCE);
    const communicationProtocol = codelabGenerator.valueToCode(block, 'communication_protocol', codelabGenerator.PRECEDENCE);
    const database = codelabGenerator.valueToCode(block, 'database', codelabGenerator.PRECEDENCE);
    const externalCalls = toJSONProperty('"externalCalls"', toJSONList(codelabGenerator.statementToCode(block, 'external_calls', codelabGenerator.PRECEDENCE)));
    const code = toJSONObject(indent(JSONJoin([name, port, communicationProtocol, database, externalCalls])));
    return code;
};

codelabGenerator['route'] = function (block) {
    const serviceName = codelabGenerator.valueToCode(block, 'name', codelabGenerator.PRECEDENCE);
    const path = codelabGenerator.valueToCode(block, 'path', codelabGenerator.PRECEDENCE);
    const code = toJSONObject(indent(JSONJoin([serviceName, path])));
    return code;
}

codelabGenerator['gateway'] = function (block) {
    const port = codelabGenerator.valueToCode(block, 'port', codelabGenerator.PRECEDENCE);
    const routes = toJSONProperty('"routes"', toJSONList(codelabGenerator.statementToCode(block, 'routes')));
    const code = toJSONProperty('"gateway"', toJSONObject(indent(JSONJoin([port, routes]))));
    return [code, codelabGenerator.PRECEDENCE];
};

codelabGenerator['registry'] = function (block) {
    const port = codelabGenerator.valueToCode(block, 'port', codelabGenerator.PRECEDENCE);
    const services = '"services": [\n' + codelabGenerator.statementToCode(block, 'services') + '\n]';
    const gateway = codelabGenerator.valueToCode(block, 'gateway', codelabGenerator.PRECEDENCE);
    const tracer = codelabGenerator.valueToCode(block, 'tracer', codelabGenerator.PRECEDENCE);
    const code = toJSONObject(indent(toJSONProperty('"registry"', toJSONObject(indent(JSONJoin([port, services, gateway, tracer]))))));
    return code;
};

codelabGenerator['test_methods'] = function (block) {
    return '"test_methods"';
};

codelabGenerator['test_http_ok'] = function (block) {
    return '"test_http_ok"';
};

codelabGenerator['rest_api'] = function (block) {
    const path = codelabGenerator.valueToCode(block, 'path', codelabGenerator.PRECEDENCE);
    const methods = '"methods": [\n' + codelabGenerator.statementToCode(block, 'methods') + '\n]';
    const tests = '"tests": [\n' + codelabGenerator.statementToCode(block, 'tests') + '\n]';
    const code = toJSONProperty('"rest_api"', toJSONObject(indent(JSONJoin([path, methods, tests]))));
    return [code, codelabGenerator.PRECEDENCE];
};

codelabGenerator['method'] = function (block) {
    const type = toJSONProperty('"type"', toJSONString(block.getFieldValue('type')));
    const path = codelabGenerator.valueToCode(block, 'endpoint_path', codelabGenerator.PRECEDENCE);
    const name = codelabGenerator.valueToCode(block, 'name', codelabGenerator.PRECEDENCE);
    const request = toJSONProperty('"request"', codelabGenerator.valueToCode(block, 'request', codelabGenerator.PRECEDENCE));
    const response = toJSONProperty('"response"', codelabGenerator.valueToCode(block, 'response', codelabGenerator.PRECEDENCE));
    const code = toJSONObject(indent(JSONJoin([type, path, name, request, response])));
    return code;
};

codelabGenerator['database'] = function (block) {
    const host = codelabGenerator.valueToCode(block, 'host', codelabGenerator.PRECEDENCE);
    const port = codelabGenerator.valueToCode(block, 'port', codelabGenerator.PRECEDENCE);
    const username = codelabGenerator.valueToCode(block, 'username', codelabGenerator.PRECEDENCE);
    const password = codelabGenerator.valueToCode(block, 'password', codelabGenerator.PRECEDENCE);
    const entities = '"entities": [\n' + codelabGenerator.statementToCode(block, 'entities') + '\n]';
    const code = toJSONProperty('"database"', toJSONObject(indent(JSONJoin([host, port, username, password, entities]))));
    return [code, codelabGenerator.PRECEDENCE];
};

codelabGenerator['entity'] = function (block) {
    const name = codelabGenerator.valueToCode(block, 'name', codelabGenerator.PRECEDENCE);
    const fields = '"fields": [\n' + codelabGenerator.statementToCode(block, 'fields') + '\n]';
    const code = toJSONProperty('"entity"', toJSONObject(indent(JSONJoin([name, fields]))));
    return code;
};

codelabGenerator['field'] = function (block) {
    const type = codelabGenerator.valueToCode(block, 'type', codelabGenerator.PRECEDENCE);
    const name = codelabGenerator.valueToCode(block, 'name', codelabGenerator.PRECEDENCE);
    const code = toJSONProperty('"field"', toJSONObject(indent(JSONJoin([type, name]))));
    return code;
};

codelabGenerator['tracer'] = function (block) {
    const host = codelabGenerator.valueToCode(block, 'host', codelabGenerator.PRECEDENCE);
    const port = codelabGenerator.valueToCode(block, 'port', codelabGenerator.PRECEDENCE);
    const code = toJSONProperty('"tracer"', toJSONObject(indent(JSONJoin([host, port]))));
    return code;
};

codelabGenerator['rest_call'] = function (block) {
    const type = toJSONProperty('"type"', toJSONString(block.getFieldValue('type')));
    const url = codelabGenerator.valueToCode(block, 'url', codelabGenerator.PRECEDENCE);
    const request = toJSONProperty('"request"', codelabGenerator.valueToCode(block, 'request', codelabGenerator.PRECEDENCE));
    const response = toJSONProperty('"response"', codelabGenerator.valueToCode(block, 'response', codelabGenerator.PRECEDENCE));
    const code = toJSONProperty('"method"', toJSONObject(indent(JSONJoin([type, url, request, response]))));
    return code;
};

codelabGenerator.scrub_ = function (block, code, opt_thisOnly) {
    const nextBlock =
        block.nextConnection && block.nextConnection.targetBlock();
    let nextCode = '';
    if (nextBlock) {
        nextCode =
            opt_thisOnly ? '' : ',\n' + codelabGenerator.blockToCode(nextBlock);
    }
    return code + nextCode;
};

function toJSONProperty(key, value) {
    return key + ': ' + value;
}

function JSONJoin(values) {
    return Object.values(values).filter(Boolean).join(',\n');
}

function toJSONString(value) {
    return '"' + value + '"';
}

function toJSONObject(value) {
    return '{\n' + value + '\n}';
}

function toJSONList(value) {
    return '[\n' + value + '\n]';
}

function indent(value) {
    return codelabGenerator.prefixLines(value, codelabGenerator.INDENT);
}
