using UnityEngine;
using System.Collections;

public interface IKinect  {
	bool GetHandClosed(int handID);
    Vector3 GetPosition(int handID);
}
