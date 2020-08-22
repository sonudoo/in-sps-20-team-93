import React from 'react';
import { render, getByPlaceholderText } from '@testing-library/react';
import { SubmitJobForm } from './SubmitJobForm';

let ajaxArgument;
jest.mock('jquery', () => ({ ajax: (argument) => { ajaxArgument = argument } }));
beforeEach(() => jest.resetModules());

test('renders <input /> with placeholder Name', () => {
  const { getByPlaceholderText } = render(<SubmitJobForm />);
  const linkElement = getByPlaceholderText('Name');
  expect(linkElement).toBeInTheDocument();
});

test('renders <input /> with placeholder Phone', () => {
  const { getByPlaceholderText } = render(<SubmitJobForm />);
  const linkElement = getByPlaceholderText('Phone');
  expect(linkElement).toBeInTheDocument();
});

test('renders <input /> with placeholder Latitudes', () => {
  const { getByPlaceholderText } = render(<SubmitJobForm />);
  const linkElement = getByPlaceholderText('Latitudes');
  expect(linkElement).toBeInTheDocument();
});

test('renders <input /> with placeholder Longitudes', () => {
  const { getByPlaceholderText } = render(<SubmitJobForm />);
  const linkElement = getByPlaceholderText('Longitudes');
  expect(linkElement).toBeInTheDocument();
});

it('calls the callback when $.ajax requests are finished', () => {
  const { getByText } = render(<SubmitJobForm />);
  const $ = require('jquery');
  const submitButton = document.getElementById('SubmitButton');
  submitButton.click();
  expect(ajaxArgument.type).toEqual('POST');
  ajaxArgument.success();
  const responseElement = getByText('Request Submitted!');
  expect(responseElement).toBeInTheDocument();

});



