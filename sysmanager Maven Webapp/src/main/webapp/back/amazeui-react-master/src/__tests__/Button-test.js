jest.dontMock('../Button.js');

import React from 'react';
import ReactDOM from 'react-dom';
import TestUtils from 'react-addons-test-utils';
import Button from '../Button';

describe('Button', () => {
  it('Should output a button', () => {
    var instance = TestUtils.renderIntoDocument(
      <Button>Title</Button>
    );

    expect(ReactDOM.findDOMNode(instance).nodeName).toEqual('BUTTON');
  });

  it('Should output a component with button classes', () => {
    var instance = TestUtils.renderIntoDocument(
      <Button>Title</Button>
    );

    expect(ReactDOM.findDOMNode(instance).getAttribute('class')).
      toEqual('am-btn am-btn-default');
  });

  it('Should have type=button by default', () => {
    var instance = TestUtils.renderIntoDocument(
      <Button>
        Title
      </Button>
    );

    expect(ReactDOM.findDOMNode(instance).getAttribute('type')).toEqual('button');
  });

  it('Should show the type if set', () => {
    var instance = TestUtils.renderIntoDocument(
      <Button type="submit">
        Title
      </Button>
    );

    expect(ReactDOM.findDOMNode(instance).getAttribute('type')).toEqual('submit');
  });

  it('Should output an anchor if set href prop', () => {
    var href = '/react';
    var instance = TestUtils.renderIntoDocument(
      <Button href={href}>
        Title
      </Button>
    );

    expect(ReactDOM.findDOMNode(instance).nodeName).toEqual('A');
    expect(ReactDOM.findDOMNode(instance).getAttribute('href')).toEqual(href);
  });

  it('Should call onClick callback', () => {
    var called = false;
    var onDone = () => {
      called = true;
    };
    var instance = TestUtils.renderIntoDocument(
      <Button onClick={onDone}>
        Title
      </Button>
    );

    TestUtils.Simulate.click(ReactDOM.findDOMNode(instance));
    expect(called).toBeTruthy();
  });

  it('Should be disabled', () => {
    var instance = TestUtils.renderIntoDocument(
      <Button disabled>
        Title
      </Button>
    );

    expect(ReactDOM.findDOMNode(instance).disabled).toBeTruthy();
  });

  it('Should be disabled link', () => {
    var instance = TestUtils.renderIntoDocument(
      <Button disabled href='#'>
        Title
      </Button>
    );

    expect(ReactDOM.findDOMNode(instance).className.match(/\bam-disabled\b/)).
      toBeTruthy();
  });

  it('Should have block class', () => {
    var instance = TestUtils.renderIntoDocument(
      <Button block>
        Title
      </Button>
    );

    expect(ReactDOM.findDOMNode(instance).className.match(/\bam-btn-block\b/)).
      toBeTruthy();
  });

  it('Should apply amStyle class', () => {
    var instance = TestUtils.renderIntoDocument(
      <Button amStyle="danger">
        Title
      </Button>
    );

    expect(ReactDOM.findDOMNode(instance).className.match(/\bam-btn-danger\b/)).
      toBeTruthy();
  });

  it('Should apply amSize class', () => {
    var instance = TestUtils.renderIntoDocument(
      <Button amSize="sm">
        Title
      </Button>
    );

    expect(ReactDOM.findDOMNode(instance).className.match(/\bam-btn-sm\b/)).
      toBeTruthy();
  });

  it('Should honour additional classes set, adding not overriding', () => {
      var instance = TestUtils.renderIntoDocument(
        <Button className="test" amStyle="danger">
          Title
        </Button>
      );

      expect(ReactDOM.findDOMNode(instance).className.match(/\btest\b/)).
        toBeTruthy();
      expect(ReactDOM.findDOMNode(instance).className.match(/\bbtn-danger\b/)).
        toBeTruthy();
    });

  it('Should default to amStyle="default"', () => {
    var instance = TestUtils.renderIntoDocument(
      <Button amStyle='default'>
        Title
      </Button>
    );

    expect(instance.props.amStyle).toBe('default');
  });

  it('Should be active', () => {
    var instance = TestUtils.renderIntoDocument(
      <Button active>
        Title
      </Button>
    );

    expect(ReactDOM.findDOMNode(instance).className.match(/\bam-active\b/)).
      toBeTruthy();
  });

  it('Should be round', () => {
    var instance = TestUtils.renderIntoDocument(
      <Button round>
        Title
      </Button>
    );

    expect(ReactDOM.findDOMNode(instance).className.match(/\bam-round\b/)).
      toBeTruthy();
  });

  it('Should be radius', () => {
    var instance = TestUtils.renderIntoDocument(
      <Button radius>
        Title
      </Button>
    );

    expect(ReactDOM.findDOMNode(instance).className.match(/\bam-radius\b/)).
      toBeTruthy();
  });
});
