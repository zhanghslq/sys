import React from 'react';

import {
  Button,
  Modal,
  ModalTrigger,
} from '../AMUIReact';

export class ModalDemo extends React.Component {
  render() {
    const modal = <Modal title="Amaze UI Modal">这一个 Modal 窗口。</Modal>;

    return (
      <div>
        <h2>Modal</h2>
        <h3>基本样式</h3>
        <ModalTrigger modal={modal}>
          <Button amStyle='primary'>打开 Modal 窗口</Button>
        </ModalTrigger>
      </div>
    );
  }
}
