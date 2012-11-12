/***********************************************************************************************************************
 *
 * Copyright (C) 2010-2012 by the Stratosphere project (http://stratosphere.eu)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 **********************************************************************************************************************/

package eu.stratosphere.nephele.taskmanager.routing;

import eu.stratosphere.nephele.event.task.AbstractEvent;
import eu.stratosphere.nephele.taskmanager.transferenvelope.TransferEnvelope;

/**
 * This event is sent by an {@link InputChannelContext}. It indicates that the input channel context has received a
 * {@link TransferEnvelope} with a lower sequence number than expected. The typical reason for this is that data is
 * being replayed from a checkpoint. With the help of this event it is possible to request the sender to skip sending
 * transfer envelopes up to the given expected sequence number.
 * 
 * @author warneke
 */
public final class UnexpectedEnvelopeEvent extends AbstractEvent {

	/**
	 * The expected sequence number.
	 */
	private final int expectedSequenceNumber;

	/**
	 * Constructs a new unexpected envelope event.
	 * 
	 * @param expectedSequenceNumber
	 *        the expected sequence number
	 */
	public UnexpectedEnvelopeEvent(final int expectedSequenceNumber) {

		if (expectedSequenceNumber < 0) {
			throw new IllegalArgumentException("Argument expectedSequenceNumber must be non-negative.");
		}

		this.expectedSequenceNumber = expectedSequenceNumber;
	}

	/**
	 * Default constructor required by kryo.
	 */
	@SuppressWarnings("unused")
	private UnexpectedEnvelopeEvent() {
		this.expectedSequenceNumber = 0;
	}

	/**
	 * Returns the expected sequence number.
	 * 
	 * @return the expected sequence number
	 */
	public int getExpectedSequenceNumber() {
		return this.expectedSequenceNumber;
	}
}
