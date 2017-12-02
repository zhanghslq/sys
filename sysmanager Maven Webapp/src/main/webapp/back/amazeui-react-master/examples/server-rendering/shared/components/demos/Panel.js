import React from 'react';

import {
  PanelGroup,
  Panel,
} from '../AMUIReact';

export class PanelDemo extends React.Component {
  render() {
    return (
      <div>
        <h2>Panel</h2>
        <h3>可折叠面板组</h3>
        <PanelGroup defaultActiveKey="2" accordion>
          <Panel header="面板 1" eventKey="1">面板 1 内容</Panel>
          <Panel header="面板 2" eventKey="2">面板 2 内容</Panel>
        </PanelGroup>
      </div>
    );
  }
}
