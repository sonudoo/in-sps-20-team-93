import React from 'react';
import { render } from '@testing-library/react';
import { SubmitJobForm } from './SubmitJobForm';

global.fetch = jest.fn(() =>
  Promise.resolve({
    json: () => Promise.resolve({}),
  })
);

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

it('calls the $.ajax requests when submit button is clicked', () => {
  const { getByText } = render(<SubmitJobForm />);
  const $ = require('jquery');
  const submitButton = document.getElementById('SubmitButton');

  submitButton.click();

  expect(ajaxArgument.type).toEqual('POST');
});

it('Request Submitted is displayed when success callback is called', () => {
  const { getByText } = render(<SubmitJobForm />);
  const $ = require('jquery');
  const submitButton = document.getElementById('SubmitButton');

  submitButton.click();
  ajaxArgument.success();
  const successResponseElement = getByText('Request Submitted');

  expect(successResponseElement).toBeInTheDocument();
});

it('Bad Request is displayed when error callback is called', () => {
  const { getByText } = render(<SubmitJobForm />);
  const $ = require('jquery');
  const submitButton = document.getElementById('SubmitButton');

  submitButton.click();
  ajaxArgument.error();
  const errorResponseElement = getByText('Phone is empty or incorrect');
  
  expect(errorResponseElement).toBeInTheDocument();
});