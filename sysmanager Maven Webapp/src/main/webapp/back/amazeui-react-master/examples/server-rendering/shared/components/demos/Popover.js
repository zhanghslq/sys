import React from 'react';

import {
  Button,
  ButtonToolbar,
  PopoverTrigger,
  Popover,
} from '../AMUIReact';

export class PopoverDemo extends React.Component {
  render() {
    var placements = ['Left', 'Top', 'Bottom', 'Right'];
    var styles = ['primary', 'secondary', 'success', 'warning'];
    
    return (
      <div>
        <h2>Popover</h2>
        <h3>Hover 显示</h3>
        <ButtonToolbar>
          {
            placements.map(function(placement, i) {
              return (
                <PopoverTrigger
                  key={i}
                  placement={placement.toLowerCase()}
                  popover={<Popover><strong>{placement}</strong> 显示的 Popover</Popover>}>
                  <Button amStyle={styles[i]}>{placement} Popover</Button>
                </PopoverTrigger>
              );
            })
          }
        </ButtonToolbar>
      </div>
    );
  }
}
